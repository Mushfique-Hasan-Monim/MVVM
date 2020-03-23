package com.blackice.business.view.base

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.blackice.business.MyApp
import com.blackice.business.R
import com.blackice.business.data.DataManager
import com.blackice.business.databinding.ToolbarLayoutBinding
import com.blackice.business.util.IObserverCallBack
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), IObserverCallBack {

    private lateinit var dialogs: ProgressDialog

    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        dialogs =  ProgressDialog(this)
        viewRelatedTask()
    }

    abstract fun viewRelatedTask()

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        this.overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(R.anim.activity_in_back, R.anim.activity_out_back)

    }

    fun setToolbar(
        context: Context,
        toolbar: ToolbarLayoutBinding,
        title: String,
        isBackPressed: Boolean
    ) {
        toolbar.drawerTitle.setText(title)
        if (isBackPressed) {
            toolbar.drawerNavigationIcon.isVisible = true
            toolbar.drawerNavigationIcon.setImageResource(R.drawable.ic_left_arrow)
            toolbar.drawerNavigationIcon.setOnClickListener({ view -> onBackPressed() })
        } else {
            toolbar.drawerNavigationIcon.isVisible = false
            toolbar.drawerNavigationIcon.setImageResource(R.drawable.menu)
            toolbar.drawerNavigationIcon.setOnClickListener({ view ->

            })
        }
    }


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

    fun hideKeyboard() {

        val inputManager = this.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        val focusedView = this.getCurrentFocus()

        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(
                focusedView!!.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS
            )
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
        if (!this.isFinishing && dialogs.isShowing) {
            dialogs.dismiss()
        }
    }

    override fun onLoading(isLoader: Boolean) {
        if (isLoader) {
            showProgressDialog("Please wait")
        } else {
            hideProgressDialog()
        }

    }

    override fun onError(err: Throwable) {
        showToast(this,err.message!!)
    }
    fun addFragment(isReplace: Boolean, container: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if (isReplace) {
            transaction.replace(container, fragment)
        } else {
            transaction.add(container, fragment)
        }
        transaction.commit()
    }

    fun showDialog(isCancelAble: Boolean, dialogFragment: BaseDialogFragment) {
        dialogFragment.isCancelable = isCancelAble
        dialogFragment.show(getSupportFragmentManager().beginTransaction(), "dialog")
    }


}