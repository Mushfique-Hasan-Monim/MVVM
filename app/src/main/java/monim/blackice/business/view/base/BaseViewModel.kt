package monim.blackice.business.view.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.util.IObserverCallBack
import monim.blackice.business.util.LiveDataResult
import monim.blackice.business.util.ObserverHelper

abstract class BaseViewModel : ViewModel() {

    private val hashMap: HashMap<String, MutableLiveData<LiveDataResult<BaseModel<Any>>>> = HashMap()


    fun addLiveData(key: String): MutableLiveData<LiveDataResult<BaseModel<Any>>> {
        hashMap.put(key, MutableLiveData());
        return getLiveData(key)
    }

    fun getLiveData(key: String): MutableLiveData<LiveDataResult<BaseModel<Any>>> {
        return hashMap[key]!!
    }

    fun livedata(lifecycleOwner: LifecycleOwner,key: String): MutableLiveData<LiveDataResult<BaseModel<Any>>>{
        addLiveData(key).observe(
            lifecycleOwner,
            ObserverHelper(lifecycleOwner as IObserverCallBack, key).baseObserver
        )
        return getLiveData(key)
    }




}