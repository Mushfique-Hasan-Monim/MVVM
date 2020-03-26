package com.blackice.business.view.activity.login

import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
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
            ViewModelProviders.of(this, viewModelFactory)
                .get(LoginViewModel::class.java)



    }

    override fun viewRelatedTask() {
        addFragment(true, R.id.container, LoginFragment.newInstance("No"))
        val PERMISSIONS = arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        //String PERMISSIONS = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(
                this,
                PERMISSIONS,
                102
            )
        }
        chooseImage("monim is a good boy")
    }


    override fun onSuccess(result: LiveDataResult<Response<ResponseBody>>, key: String) {

        Log.e("callback", "success")
    }



}
