package com.blackice.business.view.adapter

import android.view.View
import android.view.ViewGroup
import com.blackice.business.view.base.BaseViewHolder

interface IAdapterListener {
    fun <T> clickListener(position: Int, model: T, view: View)
    fun  getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder
}