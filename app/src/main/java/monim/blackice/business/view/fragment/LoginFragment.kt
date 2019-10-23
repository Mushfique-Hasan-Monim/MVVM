package monim.blackice.business.view.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import monim.blackice.business.R
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.data.model.user.User
import monim.blackice.business.databinding.FragmentLoginBinding
import monim.blackice.business.util.ConstantField
import monim.blackice.business.util.LiveDataResult
import monim.blackice.business.view.activity.main.MainActivity
import monim.blackice.business.view.base.*

class LoginFragment private constructor(): BaseFragment() {


    companion object {
        private var f = LoginFragment()
        fun newInstance(title: String): LoginFragment {
            val args = Bundle()
            args.putString(ConstantField.newInstance().ACCESS_TITLE, title)
            f.arguments = args
            Log.d("TAG", f.toString())
            return f
        }
    }


    private lateinit var viewModel: LoginFragmentViewModel

    lateinit var binding: FragmentLoginBinding
    private var title: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        this.viewModel = ViewModelProviders.of(this, BaseViewmodelFactory(LoginFragmentViewModel(getDataManager()))).get(LoginFragmentViewModel::class.java)
        return binding.root
    }

    override fun viewRelatedTask() {

        title = arguments!!.getString(ConstantField.newInstance().ACCESS_TITLE)


        binding.button.setOnClickListener {

            viewModel.fetchLogin("demo","12345678",this)
        }

    }

    override fun onSuccess(result: LiveDataResult<BaseModel<Any>>, key: String) {
        when (key) {
            "login" -> {
                val baseData = result.data!!

                if (baseData.status) {
                    if (baseData.data != null) {
                        val moshi = Moshi.Builder().build()
                        val adapter = moshi.adapter(User::class.java)
                        val user = adapter.fromJsonValue(baseData.data)

                        getDataManager().mPref.prefLogin(user!!)
                        activity!!.startActivity(Intent(activity,MainActivity::class.java))


                    }

                }

            }
            "logout" -> {

            }

        }
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