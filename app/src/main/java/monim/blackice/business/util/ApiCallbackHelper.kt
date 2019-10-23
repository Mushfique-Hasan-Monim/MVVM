package sslwireless.android.easy.loyal.merchant.viewmodel.util

import androidx.lifecycle.MutableLiveData
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.util.LiveDataResult

class ApiCallbackHelper(
    liveData: MutableLiveData<LiveDataResult<BaseModel<Any>>>
) :
    MaybeObserver<BaseModel<Any>> {

    val liveData = liveData
    override fun onSubscribe(d: Disposable) {
        liveData.postValue(LiveDataResult.loading())
    }

    override fun onError(e: Throwable) {
        liveData.postValue(LiveDataResult.error(e))
    }

    override fun onSuccess(t: BaseModel<Any>) {
        liveData.postValue(LiveDataResult.succes(t) as LiveDataResult<BaseModel<Any>>)
    }

    override fun onComplete() {
    }

}