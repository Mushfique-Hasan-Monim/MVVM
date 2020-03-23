package com.blackice.business.data.model

class Dropdown() {
    var items = ArrayList<String>()
    var id = ArrayList<String>()

    constructor(initialItem:String, initialId:String) : this() {
        items.add(initialItem)
        id.add(initialId)
    }

}