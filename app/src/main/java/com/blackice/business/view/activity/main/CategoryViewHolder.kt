package com.blackice.business.view.activity.main

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.blackice.business.data.local_db.entity.Category
import com.blackice.business.databinding.ItemCategoryBinding
import com.blackice.business.view.adapter.IAdapterListener
import com.blackice.business.view.base.BaseViewHolder

class CategoryViewHolder(itemView: ViewDataBinding, context: Context) :
    BaseViewHolder(itemView.root) {

    var binding = itemView as ItemCategoryBinding
    var mContext: Context = context
    override fun <T> onBind(position: Int, itemModel: T, listener: IAdapterListener) {
        itemModel as Category

        Glide.with(mContext)
            .load("http://nurhossen.info/appsHill/${itemModel.image}")
            .into(binding.ivCategoryImage)

        binding.tvCategoryName.setText(itemModel.name)

        binding.root.setOnClickListener {
            listener.clickListener(position, itemModel, binding.root)
        }

    }
}