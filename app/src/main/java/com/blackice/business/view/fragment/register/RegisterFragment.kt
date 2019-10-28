package com.blackice.business.view.fragment.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.blackice.business.R
import com.blackice.business.databinding.FragmentRegisterBinding
import com.blackice.business.util.ConstantField
import com.blackice.business.util.LiveDataResult
import com.blackice.business.view.activity.login.LoginActivity
import com.blackice.business.view.base.BaseFragment
import com.blackice.business.view.base.BaseViewmodelFactory
import com.blackice.business.view.fragment.login.LoginFragment
import okhttp3.ResponseBody
import retrofit2.Response

class RegisterFragment : BaseFragment() {

    private lateinit var viewModel: RegisterViewmodel

    lateinit var binding: FragmentRegisterBinding
    private var title: String? = null


    companion object {
        private var f = RegisterFragment()
        fun newInstance(title: String): RegisterFragment {
            val args = Bundle()
            args.putString(ConstantField.newInstance().ACCESS_TITLE, title)
            f.arguments = args
            Log.d("TAG", f.toString())
            return f
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        this.viewModel = ViewModelProviders.of(this, BaseViewmodelFactory(
            RegisterViewmodel(
                getDataManager()
            )
        )
        ).get(RegisterViewmodel::class.java)
        return binding.root
    }


    override fun viewRelatedTask() {

        binding.btnLogin.setOnClickListener {
            val loginActivity = activity as LoginActivity

            loginActivity.addFragment(true, R.id.container, LoginFragment.newInstance("No"))
        }

    }

    override fun onSuccess(result: LiveDataResult<Response<ResponseBody>>, key: String) {

    }

    override fun onLoading(isLoader: Boolean) {

    }

    override fun onError(err: Throwable) {

    }
}
