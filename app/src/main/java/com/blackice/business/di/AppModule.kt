package com.blackice.business.di

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import com.blackice.business.BuildConfig
import com.blackice.business.data.local_db.RoomHelper
import com.blackice.business.data.network.ApiHelper
import com.blackice.business.data.network.IApiService
import com.blackice.business.data.prefence.PreferencesHelper
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@NonNull context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "${providePreference(context).prefGetToken()}")

                chain.proceed(request.build())
            }
            .addInterceptor(provideHttpLoggingInterceptor()).build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(provideOkHttpClient(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@NonNull context: Context): IApiService {
        return provideRetrofit(context).create(IApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePreference(@NonNull context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun provideRoomHelper(@NonNull context: Context): RoomHelper {
        return RoomHelper(context)
    }

    @Provides
    @Singleton
    fun provideApiHelper(@NonNull apiService: IApiService): ApiHelper {
        return ApiHelper(apiService)
    }
}