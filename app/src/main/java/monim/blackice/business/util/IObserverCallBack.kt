package monim.blackice.business.util

import monim.blackice.business.data.model.BaseModel

interface IObserverCallBack {
    abstract fun onSuccess(result: LiveDataResult<BaseModel<Any>>, key: String)
    abstract fun onLoading(isLoader: Boolean)
    abstract fun onError(err: Throwable)
}