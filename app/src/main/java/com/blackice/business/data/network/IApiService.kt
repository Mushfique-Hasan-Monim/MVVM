package com.blackice.business.data.network

import io.reactivex.Maybe
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface IApiService {


    @GET("{url}")
    abstract fun getRequest(
        @Path(value = "url", encoded = true) path: String,
        @QueryMap hashMap: Map<String, String>
    ): Maybe<Response<ResponseBody>>

    @FormUrlEncoded
    @POST("{url}")
    abstract fun postRequest(
        @Path(value = "url", encoded = true) path: String,
        @FieldMap hashMap: Map<String, String>
    ): Maybe<Response<ResponseBody>>

    @Multipart
    @Headers("Content-Type:multipart/form-data")
    @POST("{url}")
    abstract fun sendDocuments(
        @Path(value = "url", encoded = true) path: String,
        @PartMap partMap: Map<String, RequestBody>
    ): Maybe<Response<ResponseBody>>


    @Headers("Content-Type: application/json")
    @POST("{url}")
    abstract fun postRequestForRaw(
        @Path(value = "url", encoded = true) path: String,
        @Body requestBody: RequestBody
    ): Maybe<Response<ResponseBody>>

}