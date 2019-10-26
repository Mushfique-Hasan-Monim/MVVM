package monim.blackice.business.view.base

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import monim.blackice.business.MyApp
import monim.blackice.business.R
import monim.blackice.business.data.DataManager
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.data.network.IApiService
import monim.blackice.business.data.network.RetrofitFactory
import monim.blackice.business.databinding.ToolbarLayoutBinding
import monim.blackice.business.util.IObserverCallBack
import monim.blackice.business.util.LiveDataResult

abstract class BaseActivity : AppCompatActivity(), IObserverCallBack {

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewRelatedTask()
    }

    abstract fun viewRelatedTask()



    fun getDataManager(): DataManager {
        val application = applicationContext as MyApp
        return application.getDataManager()
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        this.overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(R.anim.activity_in_back, R.anim.activity_out_back)

    }

    fun setToolbar(context: Context, toolbar: ToolbarLayoutBinding, title: String, isBackPressed: Boolean) {
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