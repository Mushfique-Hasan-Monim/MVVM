package monim.blackice.business.util

import okhttp3.ResponseBody
import retrofit2.Response

interface IObserverCallBack {
    fun onSuccess(result: LiveDataResult<Response<ResponseBody>>, key: String)
    fun onLoading(isLoader: Boolean)
    fun onError(err: Throwable)
}