package com.blackice.business.view.activity.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import com.blackice.business.R
import com.blackice.business.data.DataManager
import com.blackice.business.data.local_db.entity.Category
import com.blackice.business.data.model.BaseModel
import com.blackice.business.databinding.ActivityMainBinding
import com.blackice.business.util.LiveDataResult
import com.blackice.business.view.activity.article.ArticleListActivity
import com.blackice.business.view.adapter.IAdapterListener
import com.blackice.business.view.base.BaseActivity
import com.blackice.business.view.base.BaseRecyclerAdapter
import com.blackice.business.view.base.BaseViewHolder
import com.blackice.business.view.base.BaseViewmodelFactory
import dagger.android.AndroidInjection
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : BaseActivity() {


    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        this.viewModel =
            ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)


    }

    override fun viewRelatedTask() {
        viewModel.fetchGetCategories(this)


    }

    private fun initCategoryRecyclerview(categories: List<Category>) {


        rvCategory.layoutManager = GridLayoutManager(this, 2)
        rvCategory.adapter = BaseRecyclerAdapter(this, object : IAdapterListener {
            override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
                return CategoryViewHolder(

                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context)
                        , R.layout.item_category
                        , parent, false
                    )

                    , this@MainActivity
                )

            }

            override fun <T> clickListener(position: Int, model: T, view: View) {
                model as Category
                val intent = Intent(this@MainActivity, ArticleListActivity::class.java)
                intent.putExtra("id", model.id)
                startActivity(intent)

            }
        }, categories as ArrayList)

    }

    override fun onSuccess(result: LiveDataResult<Response<ResponseBody>>, key: String) {

        val CategoryType = object : TypeToken<BaseModel<List<Category>>>() {

        }.type
        val baseData =
            Gson().fromJson<BaseModel<List<Category>>>(
                result.data!!.body()!!.string(),
                CategoryType
            )


        if (baseData.status) {

            if (baseData.data != null) {
                val categories = baseData.data!!
                dataManager.roomHelper.getDatabase().categoryDao().delete()
                dataManager.roomHelper.getDatabase().categoryDao().insert(categories!!)

                initCategoryRecyclerview(dataManager.roomHelper.getDatabase().categoryDao().getAll())
            }

        }
        Log.e("callback", "success")
    }

}
