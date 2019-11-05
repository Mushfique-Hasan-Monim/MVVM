package com.blackice.business.view.activity.login

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.blackice.business.R
import com.blackice.business.data.DataManager
import com.blackice.business.databinding.ActivityLoginBinding
import com.blackice.business.util.LiveDataResult
import com.blackice.business.view.base.BaseActivity
import com.blackice.business.view.base.BaseViewmodelFactory
import com.blackice.business.view.fragment.login.LoginFragment
import dagger.android.AndroidInjection
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        this.viewModel =
            ViewModelProviders.of(this, BaseViewmodelFactory(LoginViewModel(dataManager)))
                .get(LoginViewModel::class.java)

    }

    override fun viewRelatedTask() {
        addFragment(true, R.id.container, LoginFragment.newInstance("No"))
    }


    override fun onSuccess(result: LiveDataResult<Response<ResponseBody>>, key: String) {

        Log.e("callback","success")
    }

    override fun onLoading(isLoader: Boolean) {
        if(isLoader){
            Log.e("callback","loading")
        }else{
            Log.e("callback","stop loading")
        }

    }

    override fun onError(err: Throwable) {
        Log.e("callback","error")
    }


}
