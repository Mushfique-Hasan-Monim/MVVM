package monim.blackice.business.util

import retrofit2.http.Field


class ConstantField private constructor(){

    var ACCESS_TOKEN = "token"
    var ACCESS_TITLE = "title"


    companion object {
        private var constantField = ConstantField()
        fun newInstance(): ConstantField {
            return constantField
        }
    }
}