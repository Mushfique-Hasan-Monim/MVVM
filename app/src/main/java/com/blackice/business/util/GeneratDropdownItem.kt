package com.blackice.business.util

import com.blackice.business.data.model.Dropdown
import org.json.JSONArray

class GeneratDropdownItem {

    companion object{
        fun getDropdownItems(jsonArray: JSONArray,itemKey:String, itemIdKey:String? = null, hint:String? = null):Dropdown{
            val dropdown = Dropdown()

            if(hint!=null){
                dropdown.items.add(hint)
                dropdown.id.add("0")
            }else{
                dropdown.items.add("Select your item")
                dropdown.id.add("0")
            }


            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)

                dropdown.items.add(item.getString(itemKey))

                itemIdKey?.let {
                    dropdown.id.add(item.getString(itemIdKey))
                }

            }

            return dropdown
        }

    }
}