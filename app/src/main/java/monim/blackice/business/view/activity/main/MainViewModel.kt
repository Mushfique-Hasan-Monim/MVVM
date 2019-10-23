package monim.blackice.business.view.activity.main

import androidx.lifecycle.LifecycleOwner
import monim.blackice.business.data.DataManager
import monim.blackice.business.data.model.user.User
import monim.blackice.business.view.base.BaseActivity
import monim.blackice.business.view.base.BaseViewModel
import sslwireless.android.easy.loyal.merchant.viewmodel.util.ApiCallbackHelper

class MainViewModel(dataManager: DataManager) : BaseViewModel() {
    val dataManager = dataManager


    public fun fetchGetCategories(lifecycleOwner: LifecycleOwner) {
        dataManager.apiHelper.apiGetAllCategory(ApiCallbackHelper(livedata(lifecycleOwner,"category")))
    }
}