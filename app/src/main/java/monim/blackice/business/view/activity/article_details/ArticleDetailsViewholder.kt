package monim.blackice.business.view.activity.article_details

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import monim.blackice.business.R
import monim.blackice.business.data.local_db.entity.Category
import monim.blackice.business.data.model.ArticleDetails
import monim.blackice.business.databinding.ItemArticleDetailsBinding
import monim.blackice.business.databinding.ItemCategoryBinding
import monim.blackice.business.view.adapter.IAdapterListener
import monim.blackice.business.view.base.BaseViewHolder

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

    }
}