package com.blackice.business.view.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blackice.business.util.IObserverCallBack
import com.blackice.business.util.LiveDataResult
import com.blackice.business.util.ObserverHelper
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