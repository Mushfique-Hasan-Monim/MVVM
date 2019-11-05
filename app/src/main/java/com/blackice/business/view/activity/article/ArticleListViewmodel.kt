package com.blackice.business.view.activity.article

import androidx.lifecycle.LifecycleOwner
import com.blackice.business.data.DataManager
import com.blackice.business.view.base.BaseViewModel
import sslwireless.android.easy.loyal.merchant.viewmodel.util.ApiCallbackHelper
import javax.inject.Inject

class ArticleListViewmodel @Inject constructor(dataManager: DataManager) : BaseViewModel() {

    val dataManager = dataManager

    fun fetchGetArticle(categoryId: Int, lifecycleOwner: LifecycleOwner) {
        dataManager.apiHelper.apiGetAllArticles(categoryId, ApiCallbackHelper(livedata(lifecycleOwner,"article")))
    }
}