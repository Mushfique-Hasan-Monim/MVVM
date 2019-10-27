package monim.blackice.business.data.network

import io.reactivex.schedulers.Schedulers
import monim.blackice.business.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor(){


    companion object {
        lateinit var iApiHelper:IApiService
        internal fun providePostApi(token:String): IApiService {
            val retrofit: Retrofit =
                provideRetrofitInterface(token)
            iApiHelper =  retrofit.create(IApiService::class.java)
            return iApiHelper;
        }

        internal fun provideRetrofitInterface(token:String): Retrofit {

            var client: OkHttpClient.Builder = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "$token")

                    chain.proceed(request.build())
                }
                .addInterceptor(DefaultInterceptors().httpBodyLoggingInterceptor)

            return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client.build())
                .build()
        }

        class DefaultInterceptors {

            val httpBodyLoggingInterceptor: HttpLoggingInterceptor
                get() {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                    return interceptor
                }

        }


    }

}