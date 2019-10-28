package com.blackice.business.data.network.api_call_factory

import io.reactivex.Maybe
import com.blackice.business.data.network.IApiService
import okhttp3.ResponseBody
import retrofit2.Response

class ApiGetCall() : IApiCall {

    override fun<T> getMaybeObserVable(apiService: IApiService, path: String, hashMap: HashMap<String,T>): Maybe<Response<ResponseBody>> {
        return apiService.getRequest(path,hashMap as HashMap<String,String>)
    }

}