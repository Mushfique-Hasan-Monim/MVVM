package com.blackice.business.view.activity.article_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_article_details.*

import com.blackice.business.R
import com.blackice.business.data.DataManager
import com.blackice.business.data.model.ArticleDetails
import com.blackice.business.data.model.ArticleDetailsRespons
import com.blackice.business.data.model.BaseModel
import com.blackice.business.databinding.ActivityArticleDetailsBinding
import com.blackice.business.util.LiveDataResult
import com.blackice.business.view.adapter.IAdapterListener
import com.blackice.business.view.base.BaseActivity
import com.blackice.business.view.base.BaseRecyclerAdapter
import com.blackice.business.view.base.BaseViewHolder
import com.blackice.business.view.base.BaseViewmodelFactory
import com.blackice.business.view.dialog.DialogShowImage
import dagger.android.AndroidInjection
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

class ArticleDetailsActivity : BaseActivity() {
    lateinit var binding: ActivityArticleDetailsBinding
    lateinit var viewmodel: ArticleDetailsViewmodel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_details)

        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(
            ArticleDetailsViewmodel::class.java
        )
    }

    override fun viewRelatedTask() {
        setToolbar(this, binding.toolbar, "Article Details", true)
        viewmodel.fetchGetArticleDetails(intent.getStringExtra("articleId"), this)
    }

    private fun initArticleDetails(articleDetailsList: List<ArticleDetails>) {
        rvArticleDetails.layoutManager = LinearLayoutManager(this)
        rvArticleDetails.adapter = BaseRecyclerAdapter(this, object : IAdapterListener {
            override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
                return ArticleDetailsViewholder(

                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context)
                        , R.layout.item_article_details
                        , parent, false
                    )

                    , this@ArticleDetailsActivity
                )

            }

            override fun <T> clickListener(position: Int, model: T, view: View) {
                model as ArticleDetails
                when (view.id) {
                    R.id.ivArticleDetails -> {
                        showDialog(true, DialogShowImage.newInstance(model.extended_details))
                    }
                }

            }
        }, articleDetailsList as ArrayList)
    }

    override fun onSuccess(result: LiveDataResult<Response<ResponseBody>>, key: String) {

        val articleDetailsType = object : TypeToken<BaseModel<ArticleDetailsRespons>>() {

        }.type
        val baseData =
            Gson().fromJson<BaseModel<ArticleDetailsRespons>>(
                result.data!!.body()!!.string(),
                articleDetailsType
            )

        if (baseData.status) {

            if (baseData.data != null) {
                val articleDetailsRespons = baseData.data
                initArticleDetails(articleDetailsRespons!!.article_details)
            }

        }

        Log.e("callback", "success")
    }


}
