package monim.blackice.business.util

import androidx.lifecycle.Observer
import monim.blackice.business.data.model.BaseModel
import retrofit2.HttpException
import java.lang.Exception

class ObserverHelper(iObserverCallBack: IObserverCallBack, key: String) {


    var baseObserver = Observer<LiveDataResult<BaseModel<Any>>> { result ->

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
