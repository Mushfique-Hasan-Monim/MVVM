package monim.blackice.business.view.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import monim.blackice.business.R
import monim.blackice.business.databinding.DialogShowImageBinding
import monim.blackice.business.util.LiveDataResult
import monim.blackice.business.view.base.BaseDialogFragment
import okhttp3.ResponseBody
import retrofit2.Response

class DialogShowImage private constructor(): BaseDialogFragment() {


    lateinit var binding: DialogShowImageBinding
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageUrl = arguments!!.getString("imageUrl")
        val style = STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_show_image, container, false);
        return binding.root
    }
    override fun viewRelatedTask() {

        Log.i("image","http://nurhossen.info/appsHill/${imageUrl}")
        Glide.with(activity!!)
            .load("http://nurhossen.info/appsHill/${imageUrl}")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder))
            .into(binding.ivArticleDetails)


        binding.ivCancel.setOnClickListener {
            dismiss()
        }


    }

    override fun onSuccess(result: LiveDataResult<Response<ResponseBody>>, key: String) {

    }

    override fun onLoading(isLoader: Boolean) {

    }

    override fun onError(err: Throwable) {

    }

    companion object {
        private var f = DialogShowImage()
        fun newInstance(addAmount: String): DialogShowImage {
            val args = Bundle()
            args.putString("imageUrl", addAmount)
            f.arguments = args
            return f
        }
    }
}