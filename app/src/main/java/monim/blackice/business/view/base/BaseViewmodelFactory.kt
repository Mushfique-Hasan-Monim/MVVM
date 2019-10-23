package monim.blackice.business.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseViewmodelFactory(
    private val baseViewModel: BaseViewModel
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return baseViewModel as T
    }
}