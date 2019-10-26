package monim.blackice.business.view.activity.article

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import monim.blackice.business.R
import monim.blackice.business.data.local_db.entity.Article
import monim.blackice.business.databinding.ItemArticleBinding
import monim.blackice.business.view.adapter.IAdapterListener
import monim.blackice.business.view.base.BaseViewHolder

class ArticleViewholder(itemView: ViewDataBinding, context: Context) :
    BaseViewHolder(itemView.root) {

    var binding = itemView as ItemArticleBinding
    var mContext: Context = context
    override fun <T> onBind(position: Int, itemModel: T, listener: IAdapterListener) {
        itemModel as Article

        Glide.with(mContext)
            .load("http://nurhossen.info/appsHill/${itemModel.image}")
            .apply(RequestOptions.placeholderOf(R.drawable.placeholder))
            .into(binding.ivArtice)

        Glide.with(mContext)
            .load("http://nurhossen.info/appsHill/${itemModel.author_image}")
            .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.placeholder))
            .into(binding.ivPubllisherImage)

        binding.tvTitle.setText(itemModel.title)
        binding.tvPublisherName.setText(itemModel.author_name)

        binding.root.setOnClickListener {
            listener.clickListener(position, itemModel, binding.root)
        }

    }
}