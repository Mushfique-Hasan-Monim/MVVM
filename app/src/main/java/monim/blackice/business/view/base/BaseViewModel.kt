package monim.blackice.business.view.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import monim.blackice.business.util.IObserverCallBack
import monim.blackice.business.util.LiveDataResult
import monim.blackice.business.util.ObserverHelper
import okhttp3.ResponseBody
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {

    fun livedata(lifecycleOwner: LifecycleOwner,key: String): MutableLiveData<LiveDataResult<Response<ResponseBody>>>{
        var livedata = MutableLiveData<LiveDataResult<Response<ResponseBody>>>()

        livedata.observe(
            lifecycleOwner,
            ObserverHelper(lifecycleOwner as IObserverCallBack, key).baseObserver
        )
        return livedata
    }




}