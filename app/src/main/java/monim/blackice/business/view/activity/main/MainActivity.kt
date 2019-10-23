package monim.blackice.business.view.activity.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.activity_main.*
import monim.blackice.business.R
import monim.blackice.business.data.local_db.entity.Category
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.databinding.ActivityMainBinding
import monim.blackice.business.util.DateUtil
import monim.blackice.business.util.LiveDataResult
import monim.blackice.business.view.activity.login.LoginActivity
import monim.blackice.business.view.adapter.IAdapterListener
import monim.blackice.business.view.base.BaseActivity
import monim.blackice.business.view.base.BaseRecyclerAdapter
import monim.blackice.business.view.base.BaseViewHolder
import monim.blackice.business.view.base.BaseViewmodelFactory
import java.util.ArrayList

class MainActivity : BaseActivity() {


    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        this.viewModel = ViewModelProviders.of(this, BaseViewmodelFactory(MainViewModel(getDataManager()))).get(MainViewModel::class.java)


    }

    override fun viewRelatedTask() {
        viewModel.fetchGetCategories( this)


    }
    private fun initCategoryRecyclerview(categories:List<Category>){

        rvCategory.layoutManager = LinearLayoutManager(this)
        rvCategory.adapter = BaseRecyclerAdapter(this,object : IAdapterListener {
            override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
                return CategoryViewHolder(

                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context)
                        ,R.layout.item_category
                        ,parent,false)

                    ,this@MainActivity)

            }

            override fun <T> clickListener(position: Int, model: T, view: View) {

            }
        },categories as ArrayList<Category>)

    }

    override fun dataProcess(result: LiveDataResult<BaseModel<Any>>, key: String) {


        val moshi = Moshi.Builder().build()
        val typed = Types.newParameterizedType(List::class.java, Category::class.java)
        val adapter: JsonAdapter<List<Category>> = moshi.adapter(typed)


        val baseData = result.data!!
        if (baseData.status) {

            if (baseData.data != null) {
                val categories = adapter.fromJsonValue(baseData.data!!)
                getDataManager().roomHelper.getDatabase().categoryDao().delete()
                getDataManager().roomHelper.getDatabase().categoryDao().insert(categories!!)

                initCategoryRecyclerview(getDataManager().roomHelper.getDatabase().categoryDao().getAll())
            }

        }
        Log.e("callback","success")
    }


    override fun loadingProcess(isLoader: Boolean) {
        if(isLoader){
            Log.e("callback","loading")
        }else{
            Log.e("callback","stop loading")
        }

    }

    override fun errorProcess(err: Throwable) {
        Log.e("callback","error")
    }



}
