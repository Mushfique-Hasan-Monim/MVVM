package monim.blackice.business.data.network.api_call_factory

import io.reactivex.Maybe
import monim.blackice.business.data.model.BaseModel
import monim.blackice.business.data.network.IApiService

class ApiGetCall() : IApiCall {

    override fun<T> getMaybeObserVable(apiService: IApiService, path: String, hashMap: HashMap<String,T>): Maybe<BaseModel<Any>> {
        return apiService.getRequest(path,hashMap as HashMap<String,String>)
    }

}