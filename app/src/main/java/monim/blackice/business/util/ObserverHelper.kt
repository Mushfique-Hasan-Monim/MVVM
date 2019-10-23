package monim.blackice.business.util

import androidx.lifecycle.Observer
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.view.base.BaseViewModel
import retrofit2.HttpException
import java.lang.Exception

class ObserverHelper(iObserverCallBack: IObserverCallBack, key: String) {


    var baseObserver = Observer<LiveDataResult<BaseModel<Any>>> { result ->

        when (result?.status) {

            LiveDataResult.Status.LOADING -> {
                iObserverCallBack.loadingProcess(true)
            }
            LiveDataResult.Status.ERROR -> {
                iObserverCallBack.loadingProcess(false)
                try {

                    val r = result.err!! as HttpException
                    val code = r.code()
                    if (code == 401) {
                        iObserverCallBack.errorProcess(result.err!!)
                    }
                } catch (e: Exception) {
                    iObserverCallBack.errorProcess(result.err!!)
                }
            }
            LiveDataResult.Status.SUCCESS -> {
                iObserverCallBack.loadingProcess(false)
                iObserverCallBack.dataProcess(result, key)

            }
        }
    }

}
