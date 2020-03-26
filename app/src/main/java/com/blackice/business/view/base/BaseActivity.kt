package com.blackice.business.view.base

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.blackice.business.MyApp
import com.blackice.business.R
import com.blackice.business.data.DataManager
import com.blackice.business.databinding.ToolbarLayoutBinding
import com.blackice.business.util.IObserverCallBack
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.base_bottom_sheet.*
import kotlinx.android.synthetic.main.base_image_choose.*
import kotlinx.android.synthetic.main.dialog_show_image.*
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), IObserverCallBack {

    private lateinit var dialogs: ProgressDialog
    private var baseBottomSheet:BottomSheetDialogFragment?=null
    var image_uri: Uri? = null

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

    fun showStatus(code: Int, heading : String, message: String) {
        hideKeyboard()
        class BaseBottomSheet : BottomSheetDialogFragment() {

            override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {

                val view = inflater.inflate(R.layout.base_bottom_sheet, container)
                return view
            }

            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)
                if (code == 200) {
                    success_fail_image.setImageResource(R.drawable.success_ic)
                    success_text.setText("SUCCESS")
                    message_text.setText(message)

                }
                else if (code == 201) {
                    success_fail_image.setImageResource(R.drawable.alert_ic)
                    success_text.setText(heading)
                    message_text.setText(message)

                }
                else {
                    success_fail_image.setImageResource(R.drawable.fail_ic)
                    success_text.setText("FAIL")
                    message_text.setText(message)
                }

                okay_button.setOnClickListener {
                    onBackPressed()
                    baseBottomSheet!!.dismiss()
                }

            }

            override fun onAttach(context: Context) {
                super.onAttach(context)

            }
        }
        baseBottomSheet = BaseBottomSheet()
        baseBottomSheet!!.show(supportFragmentManager,"1234")
    }


    fun chooseImage( title : String) {
        hideKeyboard()
        class BaseBottomSheet : BottomSheetDialogFragment() {

            override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {

                val view = inflater.inflate(R.layout.base_image_choose, container)
                return view
            }

            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)

                tvTitle.text = title

                tvCamera.setOnClickListener {
                    val values = ContentValues()
                    values.put(MediaStore.Images.Media.TITLE, "New Picture")
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
                    image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                    //camera intent
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
                    startActivityForResult(cameraIntent, 0)
                    baseBottomSheet!!.dismiss()
                }
                tvGallery.setOnClickListener {
                    val pickPhoto =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(pickPhoto, 1)

                    baseBottomSheet!!.dismiss()
                }
                btnCancel.setOnClickListener {
                    baseBottomSheet!!.dismiss()
                }
            }

            override fun onAttach(context: Context) {
                super.onAttach(context)

            }
        }
        baseBottomSheet = BaseBottomSheet()
        baseBottomSheet!!.show(supportFragmentManager,"choose_image")
    }


    fun hasPermissions(context: Context?, vararg permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            Log.e("log per", "granted 1")
            for (permission  in permissions[0]) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    Log.e("log per", "granted 2")
                    return false
                }
            }
        }
        return true
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