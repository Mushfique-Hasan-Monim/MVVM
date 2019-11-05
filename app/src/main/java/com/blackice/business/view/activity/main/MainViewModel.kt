package com.blackice.business.view.activity.main

import androidx.lifecycle.LifecycleOwner
import com.blackice.business.data.DataManager
import com.blackice.business.view.base.BaseViewModel
import sslwireless.android.easy.loyal.merchant.viewmodel.util.ApiCallbackHelper
import javax.inject.Inject

class MainViewModel @Inject constructor(dataManager: DataManager) : BaseViewModel() {
    val dataManager = dataManager


    public fun fetchGetCategories(lifecycleOwner: LifecycleOwner) {
        dataManager.apiHelper.apiGetAllCategory(ApiCallbackHelper(livedata(lifecycleOwner,"category")))
    }
}