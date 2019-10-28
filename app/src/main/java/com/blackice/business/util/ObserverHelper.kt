package com.blackice.business.util

import androidx.lifecycle.Observer
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

class ObserverHelper(iObserverCallBack: IObserverCallBack, key: String) {


    var baseObserver = Observer<LiveDataResult<Response<ResponseBody>>> { result ->

        when (result?.status) {

            LiveDataResult.Status.LOADING -> {
                iObserverCallBack.onLoading(true)
            }
            LiveDataResult.Status.ERROR -> {
                iObserverCallBack.onLoading(false)
                try {

                    val r = result.err!! as HttpException
                    val code = r.code()
                    if (code == 401) {
                        iObserverCallBack.onError(result.err!!)
                    }
                } catch (e: Exception) {
                    iObserverCallBack.onError(result.err!!)
                }
            }
            LiveDataResult.Status.SUCCESS -> {
                iObserverCallBack.onLoading(false)
                iObserverCallBack.onSuccess(result, key)

            }
        }
    }

}
