package com.blackice.business.view.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.blackice.business.R
import com.blackice.business.data.DataManager
import com.blackice.business.util.IObserverCallBack
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment : DaggerFragment(), IObserverCallBack {


    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var dialogs: ProgressDialog

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialogs =  ProgressDialog(activity!!)
        viewRelatedTask()
    }

    abstract fun viewRelatedTask()

    fun showToast(context: Context, message: String) {
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_LONG

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_toast_layout, null)

        val toastText = view.findViewById<TextView>(R.id.toastText)
        toastText.setText(message)

        toast.view = view
        toast.show()
    }

    fun showDialog(isCancelAble: Boolean, dialogFragment: BaseDialogFragment) {
        (activity as BaseActivity).showDialog(isCancelAble, dialogFragment)
    }

    override fun onLoading(isLoader: Boolean) {
        if (isLoader) {
            showProgressDialog("Please wait")
        } else {
            hideProgressDialog()
        }

    }

    fun showProgressDialog(message: String) {
        if (TextUtils.isEmpty(message)) {
            dialogs.setMessage("")
        } else {
            dialogs.setMessage(message)
        }
        if (!dialogs.isShowing) {
            dialogs.setCancelable(false)
            dialogs.show()
        }
    }

    fun hideProgressDialog() {
        if (!activity!!.isFinishing && dialogs.isShowing) {
            dialogs.dismiss()
        }
    }

    override fun onError(err: Throwable) {
        showToast(activity!!,err.message!!)
    }


}