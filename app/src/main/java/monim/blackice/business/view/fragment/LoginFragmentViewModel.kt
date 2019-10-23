package monim.blackice.business.view.fragment

import androidx.lifecycle.LifecycleOwner
import monim.blackice.business.data.DataManager
import monim.blackice.business.view.base.BaseViewModel
import sslwireless.android.easy.loyal.merchant.viewmodel.util.ApiCallbackHelper

class LoginFragmentViewModel (dataManager: DataManager) : BaseViewModel() {

    val dataManager = dataManager


    public fun fetchLogin(username: String, password: String, lifecycleOwner: LifecycleOwner) {
        dataManager.apiHelper.apiLogin(username, password, ApiCallbackHelper(livedata(lifecycleOwner,"login")))
    }

}