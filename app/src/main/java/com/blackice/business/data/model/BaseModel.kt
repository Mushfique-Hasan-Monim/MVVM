package com.blackice.business.data.model

class BaseModel<z>() {
    var status = false
    lateinit var message: String
    var data: z? = null
}