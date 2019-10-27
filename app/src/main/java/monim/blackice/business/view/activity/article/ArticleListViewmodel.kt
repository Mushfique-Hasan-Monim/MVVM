package monim.blackice.business.view.activity.article

import androidx.lifecycle.LifecycleOwner
import monim.blackice.business.data.DataManager
import monim.blackice.business.data.local_db.entity.Category
import monim.blackice.business.view.base.BaseViewModel
import sslwireless.android.easy.loyal.merchant.viewmodel.util.ApiCallbackHelper

class ArticleListViewmodel(dataManager: DataManager) : BaseViewModel() {

    val dataManager = dataManager

    fun fetchGetArticle(categoryId: Int, lifecycleOwner: LifecycleOwner) {
        dataManager.apiHelper.apiGetAllArticles(categoryId, ApiCallbackHelper(livedata(lifecycleOwner,"article")))
    }
}