package sslwireless.android.easy.loyal.merchant.viewmodel.util

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import monim.blackice.business.util.LiveDataResult
import okhttp3.ResponseBody
import retrofit2.Response

class ApiCallbackHelper(
    liveData: MutableLiveData<LiveDataResult<Response<ResponseBody>>>
) :
    MaybeObserver<Response<ResponseBody>> {

    val liveData = liveData
    override fun onSubscribe(d: Disposable) {
        liveData.postValue(LiveDataResult.loading())
    }

    override fun onError(e: Throwable) {
        liveData.postValue(LiveDataResult.error(e))
    }

    override fun onSuccess(t: Response<ResponseBody>) {
        liveData.postValue(LiveDataResult.succes(t))
    }

    override fun onComplete() {
    }

}