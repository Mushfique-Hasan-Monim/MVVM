package monim.blackice.business.view.dialog

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.gson.JsonObject
import monim.blackice.business.R
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.databinding.DialogHomeBinding
import monim.blackice.business.util.LiveDataResult
import monim.blackice.business.view.base.BaseDialogFragment
import okhttp3.ResponseBody
import retrofit2.Response

class DialogHome : BaseDialogFragment() {


    lateinit var binding: DialogHomeBinding
    private var addAmount: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addAmount = arguments!!.getString("addAmount")
        val style = STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style, theme)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_home, container, false);
        return binding.root
    }
    override fun viewRelatedTask() {
        binding.collectionAmount = addAmount
        binding.btnOk.setOnClickListener {

        }

    }

    override fun onSuccess(result: LiveDataResult<Response<ResponseBody>>, key: String) {

    }

    override fun onLoading(isLoader: Boolean) {

    }

    override fun onError(err: Throwable) {

    }

    companion object {
        private var f = DialogHome()
        fun newInstance(addAmount: String): DialogHome {
            val args = Bundle()
            args.putString("addAmount", addAmount)
            f.arguments = args
            return f
        }
    }
}