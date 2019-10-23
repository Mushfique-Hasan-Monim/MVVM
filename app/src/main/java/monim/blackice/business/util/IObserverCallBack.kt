package monim.blackice.business.util

import monim.blackice.business.data.model.BaseModel

interface IObserverCallBack {
    abstract fun dataProcess(result: LiveDataResult<BaseModel<Any>>, key: String)
    abstract fun loadingProcess(isLoader: Boolean)
    abstract fun errorProcess(err: Throwable)
}