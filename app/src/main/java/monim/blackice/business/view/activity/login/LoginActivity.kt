package monim.blackice.business.view.activity.login

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import monim.blackice.business.R
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.databinding.ActivityLoginBinding
import monim.blackice.business.util.LiveDataResult
import monim.blackice.business.view.base.BaseActivity
import monim.blackice.business.view.base.BaseViewmodelFactory
import monim.blackice.business.view.fragment.LoginFragment

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        this.viewModel =
            ViewModelProviders.of(this, BaseViewmodelFactory(LoginViewModel(getDataManager())))
                .get(LoginViewModel::class.java)

    }

    override fun viewRelatedTask() {
        addFragment(true, R.id.container, LoginFragment.newInstance("No"))
    }


    override fun onSuccess(result: LiveDataResult<BaseModel<Any>>, key: String) {

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
