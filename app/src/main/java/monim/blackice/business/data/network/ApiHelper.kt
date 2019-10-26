package monim.blackice.business.data.network

import io.reactivex.Maybe
import monim.blackice.business.data.local_db.entity.Category
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.data.network.api_call_factory.ApiGetCall
import monim.blackice.business.data.network.api_call_factory.ApiPostCall
import monim.blackice.business.data.network.api_call_factory.ApiPostCallWithDocument
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

    //api method field key
    val KEY_USER_NAME = "username"
    val KEY_PASSWORD = "password"

    val KEY_CATEGORY_ID= "category_id"

    fun apiLogin(username: String, password: String, apiCallbackHelper: ApiCallbackHelper) {

            val hashMap = HashMap<String, String>()
            hashMap.put(KEY_USER_NAME, username)
            hashMap.put(KEY_PASSWORD, password)
            getApiCallObservable(CALL_TYPE_POST, ENDPOINT_LOGIN, hashMap).subscribe(apiCallbackHelper)
    }

    fun apiGetAllCategory(apiCallbackHelper: ApiCallbackHelper) {
        val hashMap = HashMap<String, String>()
        getApiCallObservable(CALL_TYPE_GET,ENDPOINT_GET_ALL_CATEGORY,hashMap).subscribe(apiCallbackHelper)
    }
    fun apiGetAllArticles(categoryId:Int, apiCallbackHelper: ApiCallbackHelper) {
        val hashMap = HashMap<String, String>()
        hashMap.put(KEY_CATEGORY_ID, categoryId.toString())
        getApiCallObservable(CALL_TYPE_POST,"blog/10/0/getAllArticles",hashMap).subscribe(apiCallbackHelper)
    }

    fun <T> getApiCallObservable(callType: String, path: String, hashMap: HashMap<String, T>): Maybe<BaseModel<Any>> {
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