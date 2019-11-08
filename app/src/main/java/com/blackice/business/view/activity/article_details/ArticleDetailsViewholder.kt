package com.blackice.business.view.activity.article_details

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.blackice.business.R
import com.blackice.business.data.model.ArticleDetails
import com.blackice.business.databinding.ItemArticleDetailsBinding
import com.blackice.business.view.adapter.IAdapterListener
import com.blackice.business.view.base.BaseViewHolder

class ArticleDetailsViewholder(itemView: ViewDataBinding, context: Context) :
    BaseViewHolder(itemView.root) {

    var binding = itemView as ItemArticleDetailsBinding
    var mContext: Context = context
    override fun <T> onBind(position: Int, itemModel: T, listener: IAdapterListener) {
        itemModel as ArticleDetails

        Glide.with(mContext)
            .load("http://nurhossen.info/appsHill/${itemModel.extended_details}")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder))
            .into(binding.ivArticleDetails)

        binding.tvTitle.setText(itemModel.para_title)
        binding.tvArticleDetails.setText(itemModel.para_details)

        binding.root.setOnClickListener {
            listener.clickListener(position, itemModel, binding.root)
        }
        binding.ivArticleDetails.setOnClickListener {
            listener.clickListener(position, itemModel, binding.ivArticleDetails)
        }

    }
}