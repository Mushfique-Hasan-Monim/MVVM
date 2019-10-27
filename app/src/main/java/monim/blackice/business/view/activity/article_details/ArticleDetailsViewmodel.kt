package monim.blackice.business.view.activity.article_details

import androidx.lifecycle.LifecycleOwner
import monim.blackice.business.data.DataManager
import monim.blackice.business.view.base.BaseViewModel
import sslwireless.android.easy.loyal.merchant.viewmodel.util.ApiCallbackHelper

class ArticleDetailsViewmodel(dataManager: DataManager) : BaseViewModel() {

    val dataManager = dataManager

    fun fetchGetArticleDetails(articleId: String, lifecycleOwner: LifecycleOwner) {
        dataManager.apiHelper.apiGetArticleDetails(articleId, ApiCallbackHelper(livedata(lifecycleOwner,"articleDetails")))
    }
}