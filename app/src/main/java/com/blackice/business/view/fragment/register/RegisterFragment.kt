package com.blackice.business.view.fragment.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.blackice.business.R
import com.blackice.business.data.DataManager
import com.blackice.business.databinding.FragmentRegisterBinding
import com.blackice.business.util.ConstantField
import com.blackice.business.util.LiveDataResult
import com.blackice.business.view.activity.login.LoginActivity
import com.blackice.business.view.base.BaseFragment
import com.blackice.business.view.base.BaseViewmodelFactory
import com.blackice.business.view.fragment.login.LoginFragment
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        this.viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(RegisterViewmodel::class.java)
        return binding.root
    }


    override fun viewRelatedTask() {

        binding.btnLogin.setOnClickListener {
            val loginActivity = activity as LoginActivity

            loginActivity.addFragment(true, R.id.container, LoginFragment.newInstance("No"))
        }


        binding.btnSignup.setOnClickListener {

            if (isValid()) {
                viewModel.fetchRegistration(
                    binding.edtUserName.text.toString(),
                    binding.edtName.text.toString(),
                    binding.edtEmail.text.toString(),
                    binding.edtPassword.text.toString(),
                    this
                )
            }
        }

    }

    private fun isValid(): Boolean {

        var isValid = true
        if (binding.edtName.text.toString().equals(null) && binding.edtName.text.toString().equals("")) {
            binding.edtName.setError("Please enter name")
            isValid = false
        }
        if (binding.edtUserName.text.toString().equals(null) && binding.edtUserName.text.toString().equals(
                ""
            )
        ) {
            binding.edtUserName.setError("Please enter user name")
            isValid = false
        }
        if (binding.edtPassword.text.toString().equals(null) && binding.edtPassword.text.toString().equals(
                ""
            )
        ) {
            binding.edtPassword.setError("Please enter password")
            isValid = false
        }
        if (binding.edtEmail.text.toString().equals(null) && binding.edtEmail.text.toString().equals(
                ""
            )
        ) {
            binding.edtEmail.setError("Please enter email")
            isValid = false
        }
        return isValid
    }

    override fun onSuccess(result: LiveDataResult<Response<ResponseBody>>, key: String) {

        result.data!!.body()?.let {
            val jsonObject = JSONObject(result.data!!.body()!!.string())
            if (jsonObject.get("status") == true) {
                val loginActivity = activity as LoginActivity
                loginActivity.addFragment(true, R.id.container, LoginFragment.newInstance("No"))
            } else {
                showToast(context!!, jsonObject.get("message").toString())
            }
        } ?: run {
            showToast(context!!, "Something wrong")
        }

    }

    override fun onLoading(isLoader: Boolean) {

    }

    override fun onError(err: Throwable) {

        showToast(context!!, err.message!!)
    }
}
