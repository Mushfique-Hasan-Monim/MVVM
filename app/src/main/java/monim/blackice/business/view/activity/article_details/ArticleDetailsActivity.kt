package monim.blackice.business.view.activity.article_details

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import monim.blackice.business.R
import monim.blackice.business.data.model.ArticleDetailsRespons
import monim.blackice.business.data.model.ArticleRespons
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.databinding.ActivityArticleDetailsBinding
import monim.blackice.business.databinding.ToolbarLayoutBinding
import monim.blackice.business.util.LiveDataResult
import monim.blackice.business.view.activity.article.ArticleListViewmodel
import monim.blackice.business.view.base.BaseActivity
import monim.blackice.business.view.base.BaseViewmodelFactory
import okhttp3.ResponseBody
import retrofit2.Response

class ArticleDetailsActivity : BaseActivity() {
    lateinit var binding : ActivityArticleDetailsBinding
    lateinit var viewmodel: ArticleDetailsViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_article_details)
        viewmodel = ViewModelProviders.of(this, BaseViewmodelFactory(ArticleDetailsViewmodel(getDataManager()))).get(
            ArticleDetailsViewmodel::class.java)
    }

    override fun viewRelatedTask() {
        setToolbar(this, binding.toolbar,"Article Details", true)
        viewmodel.fetchGetArticleDetails(intent.getStringExtra("articleId"),this)
    }

    override fun onSuccess(result: LiveDataResult<Response<ResponseBody>>, key: String) {

        val articleDetailsType = object : TypeToken<BaseModel<ArticleDetailsRespons>>() {

        }.type
        val baseData =
            Gson().fromJson<BaseModel<ArticleDetailsRespons>>(result.data!!.body()!!.string(), articleDetailsType)

        if (baseData.status) {

            if (baseData.data != null) {
                val articleRespons = baseData.data
//                initArticle(articleRespons!!.articles)
            }

        }

        Log.e("callback", "success")
    }

    override fun onLoading(isLoader: Boolean) {

    }

    override fun onError(err: Throwable) {

    }
}
