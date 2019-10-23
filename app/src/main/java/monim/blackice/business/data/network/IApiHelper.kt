package monim.blackice.business.data.network

import sslwireless.android.easy.loyal.merchant.viewmodel.util.ApiCallbackHelper


interface IApiHelper {
    fun apiLogin(username: String, password: String,apiCallbackHelper: ApiCallbackHelper)
    fun apiGetAllCategory(apiCallbackHelper: ApiCallbackHelper)
}