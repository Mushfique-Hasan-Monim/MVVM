package com.blackice.business.view.fragment.login

import androidx.lifecycle.LifecycleOwner
import com.blackice.business.data.DataManager
import com.blackice.business.view.base.BaseViewModel
import sslwireless.android.easy.loyal.merchant.viewmodel.util.ApiCallbackHelper

class LoginFragmentViewModel (dataManager: DataManager) : BaseViewModel() {

    val dataManager = dataManager


    public fun fetchLogin(username: String, password: String, lifecycleOwner: LifecycleOwner) {
        dataManager.apiHelper.apiLogin(username, password, ApiCallbackHelper(livedata(lifecycleOwner,"login")))
    }

}