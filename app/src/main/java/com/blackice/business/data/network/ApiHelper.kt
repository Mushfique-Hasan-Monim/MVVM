package com.blackice.business.data.network

import io.reactivex.Maybe
import com.blackice.business.data.network.api_call_factory.ApiGetCall
import com.blackice.business.data.network.api_call_factory.ApiPostCall
import com.blackice.business.data.network.api_call_factory.ApiPostCallWithDocument
import okhttp3.ResponseBody
import retrofit2.Response
import sslwireless.android.easy.loyal.merchant.viewmodel.util.ApiCallbackHelper

class ApiHelper(apiService: IApiService) {


    val apiService = apiService

    //call type
    val CALL_TYPE_GET = "get"
    val CALL_TYPE_POST = "post"
    val CALL_TYPE_POST_WITH_DOCUMENT = "post with document"

    //endpoint
    val ENDPOINT_LOGIN = "user/login"
    val ENDPOINT_GET_ALL_CATEGORY = "blog/getAllCategories"
    val ENDPOINT_REGISTRATION = "user/register"

    //api method field key
    val KEY_USER_NAME = "username"
    val KEY_PASSWORD = "password"

    val KEY_FULL_NAME = "full_name"
    val KEY_EMAIL = "email"
    val KEY_CONFIRM_PASSWORD = "confirm_password"
    val KEY_USER_TYPE = "user_type"

    val KEY_CATEGORY_ID= "category_id"

    fun apiLogin(username: String, password: String, apiCallbackHelper: ApiCallbackHelper) {

            val hashMap = HashMap<String, String>()
            hashMap.put(KEY_USER_NAME, username)
            hashMap.put(KEY_PASSWORD, password)
            getApiCallObservable(CALL_TYPE_POST, ENDPOINT_LOGIN, hashMap).subscribe(apiCallbackHelper)
    }
    fun apiRegistration(username: String,fullname: String,email: String, password: String, apiCallbackHelper: ApiCallbackHelper) {

            val hashMap = HashMap<String, String>()
            hashMap.put(KEY_USER_NAME, username)
            hashMap.put(KEY_FULL_NAME, fullname)
            hashMap.put(KEY_EMAIL, email)
            hashMap.put(KEY_PASSWORD, password)
            hashMap.put(KEY_CONFIRM_PASSWORD, password)
            hashMap.put(KEY_USER_TYPE, "general")
            getApiCallObservable(CALL_TYPE_POST, ENDPOINT_REGISTRATION, hashMap).subscribe(apiCallbackHelper)
    }

    fun apiGetAllCategory(apiCallbackHelper: ApiCallbackHelper) {
        val hashMap = HashMap<String, String>()
        getApiCallObservable(CALL_TYPE_GET,ENDPOINT_GET_ALL_CATEGORY,hashMap).subscribe(apiCallbackHelper)
    }
    fun apiGetArticleDetails(articleId:String,apiCallbackHelper: ApiCallbackHelper) {
        val hashMap = HashMap<String, String>()
        getApiCallObservable(CALL_TYPE_GET,"blog/$articleId/getArticleDetails",hashMap).subscribe(apiCallbackHelper)
    }
    fun apiGetAllArticles(categoryId:Int, apiCallbackHelper: ApiCallbackHelper) {
        val hashMap = HashMap<String, String>()
        hashMap.put(KEY_CATEGORY_ID, categoryId.toString())
        getApiCallObservable(CALL_TYPE_POST,"blog/10/0/getAllArticles",hashMap).subscribe(apiCallbackHelper)
    }

    fun <T> getApiCallObservable(callType: String, path: String, hashMap: HashMap<String, T>): Maybe<Response<ResponseBody>> {
        if (callType.equals(CALL_TYPE_GET)) {
            return ApiGetCall().getMaybeObserVable(apiService, path, hashMap!!)
        }
        else if (callType.equals(CALL_TYPE_POST)) {
            return ApiPostCall().getMaybeObserVable(apiService, path, hashMap!!)

        } else  {
            return ApiPostCallWithDocument().getMaybeObserVable(apiService, path, hashMap!!)

        }
    }

}