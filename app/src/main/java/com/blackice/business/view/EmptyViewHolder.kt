package com.blackice.business.view

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.blackice.business.databinding.EmptyPageBinding
import com.blackice.business.databinding.ItemArticleBinding
import com.blackice.business.view.adapter.IAdapterListener
import com.blackice.business.view.base.BaseViewHolder

class EmptyViewHolder (itemView: ViewDataBinding, context: Context) :
    BaseViewHolder(itemView.root) {
    var binding = itemView as EmptyPageBinding

    override fun <T> onBind(position: Int, itemModel: T, listener: IAdapterListener) {

    }
}