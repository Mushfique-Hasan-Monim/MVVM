package com.blackice.business.view.fragment.register

import androidx.lifecycle.LifecycleOwner
import com.blackice.business.data.DataManager
import com.blackice.business.view.base.BaseViewModel
import sslwireless.android.easy.loyal.merchant.viewmodel.util.ApiCallbackHelper

class RegisterViewmodel(dataManager: DataManager) : BaseViewModel() {

    val dataManager = dataManager


    public fun fetchRegistration(
        username: String,
        fullname: String,
        email: String,
        password: String,
        lifecycleOwner: LifecycleOwner
    ) {
        dataManager.apiHelper.apiRegistration(
            username,
            fullname,
            email,
            password,
            ApiCallbackHelper(livedata(lifecycleOwner, "registration"))
        )
    }

}