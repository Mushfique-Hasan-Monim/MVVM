package monim.blackice.business.view.activity.article

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
import com.squareup.moshi.Types
import monim.blackice.business.R
import monim.blackice.business.data.local_db.entity.Article
import monim.blackice.business.data.local_db.entity.Category
import monim.blackice.business.data.model.ArticleRespons
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.data.model.user.User
import monim.blackice.business.databinding.ActivityArticleListBinding
import monim.blackice.business.util.LiveDataResult
import monim.blackice.business.view.activity.main.CategoryViewHolder
import monim.blackice.business.view.adapter.IAdapterListener
import monim.blackice.business.view.base.BaseActivity
import monim.blackice.business.view.base.BaseRecyclerAdapter
import monim.blackice.business.view.base.BaseViewHolder
import monim.blackice.business.view.base.BaseViewmodelFactory
import java.util.ArrayList

class ArticleListActivity : BaseActivity() {
    private lateinit var binding: ActivityArticleListBinding
    private lateinit var viewmodel: ArticleListViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_list)
        viewmodel = ViewModelProviders.of(
            this,
            BaseViewmodelFactory(ArticleListViewmodel(getDataManager()))
        ).get(ArticleListViewmodel::class.java)


    }

    override fun viewRelatedTask() {
        setToolbar(this, binding.toolbar,"Article List",true)
        viewmodel.fetchGetArticle(intent.getIntExtra("id",0),this)

    }

    private fun initArticle(articles:List<Article>){

        binding.rvArticleList.layoutManager = LinearLayoutManager(this)
        binding.rvArticleList.adapter = BaseRecyclerAdapter(this, object: IAdapterListener{
            override fun <T> clickListener(position: Int, model: T, view: View) {

            }

            override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

                return ArticleViewholder(

                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context)
                        ,R.layout.item_article
                        ,parent,false)

                    ,this@ArticleListActivity)
            }

        },articles as ArrayList)

    }

    override fun onSuccess(result: LiveDataResult<BaseModel<Any>>, key: String) {
        val baseData = result.data!!

        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(ArticleRespons::class.java)


        if (baseData.status) {

            if (baseData.data != null) {
                val articleRespons = adapter.fromJsonValue(baseData.data)
                initArticle(articleRespons!!.articles)
            }

        }
        Log.e("callback", "success")
    }

    override fun onLoading(isLoader: Boolean) {
    }

    override fun onError(err: Throwable) {
    }
}
