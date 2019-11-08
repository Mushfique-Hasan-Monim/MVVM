package com.blackice.business.view.activity.article

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.blackice.business.R
import com.blackice.business.data.local_db.entity.Article
import com.blackice.business.databinding.ItemArticleBinding
import com.blackice.business.view.adapter.IAdapterListener
import com.blackice.business.view.base.BaseViewHolder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.text.SimpleDateFormat
import android.view.ViewTreeObserver


class ArticleViewholder(itemView: ViewDataBinding, context: Context) :
    BaseViewHolder(itemView.root) {

    var binding = itemView as ItemArticleBinding
    var mContext: Context = context

    var simpleTimeFormatFromServer: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var simpleTimeFormat: SimpleDateFormat = SimpleDateFormat("dd MMM yyyy hh:mm a")


    override fun <T> onBind(position: Int, itemModel: T, listener: IAdapterListener) {
        itemModel as Article

        Glide.with(mContext)
            .load("http://nurhossen.info/appsHill/${itemModel.image}")
            .apply(
                RequestOptions.placeholderOf(R.drawable.placeholder).transforms(
                    CenterCrop(), RoundedCorners(
                        16
                    )
                )
            )
            .into(binding.ivArtice)

        Glide.with(mContext)
            .load("http://nurhossen.info/appsHill/${itemModel.author_image}")
            .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.placeholder))
            .into(binding.ivPubllisherImage)

       binding.tvTitle.setText(itemModel.title)


        binding.tvTitle.getViewTreeObserver()
            .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.tvTitle.getViewTreeObserver().removeOnGlobalLayoutListener(this)

                    if( binding.tvTitle.getLineCount() > 1){
                        binding.tvArticleDetails.maxLines = 1
                    }
                }
            })

        binding.tvArticleDetails.setText(itemModel.description)

        simpleTimeFormatFromServer.parse(itemModel.created_date).let {
            binding.tvCreateData.setText(simpleTimeFormat.format(it))
        }


        binding.tvPublisherName.setText(itemModel.author_name)

        binding.root.setOnClickListener {
            listener.clickListener(position, itemModel, binding.root)
        }

    }
}