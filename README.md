# MVVM
Android MVVM Project example with kotlin, Livedata, Viewmodel, Dagger

Kotlin android application example with MVVM pattern, android architecture components, and kotlin coroutine.
This sample is about article read which the API i used from [Appshill](http://nurhossen.info/appsHill).
* [Kotlin](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/jetpack/arch/livedata)
* [Dagger](https://dagger.dev)
* [Room](https://developer.android.com/training/data-storage/room/) // Local data storage
* [Retrofit](https://square.github.io/retrofit/) // Networking
* [GSON](https://github.com/square/moshi) // JSON parser
* [Glide](http://square.github.io/picasso/) // Image loader


# One adapter for all recycler view: 
```javascript
binding.rvArticleList.layoutManager = LinearLayoutManager(this)
binding.rvArticleList.adapter = BaseRecyclerAdapter(this, object : IAdapterListener {
            override fun <T> clickListener(position: Int, model: T, view: View) {

                model as Article
                val intent = Intent(this@ArticleListActivity, ArticleDetailsActivity::class.java)
                intent.putExtra("articleId", model.id)
                startActivity(intent)
            }

            override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

                return ArticleViewholder(

                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context)
                        , R.layout.item_article
                        , parent, false
                    )

                    , this@ArticleListActivity
                )
            }

        }, articles as ArrayList)

```

# Viewholder Class Example:  

```javascript

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

```

# ApiHelper Class Like:   
```javascript
//call type
    val CALL_TYPE_GET = "get"
    val CALL_TYPE_POST = "post"
    val CALL_TYPE_POST_WITH_DOCUMENT = "post with document"
 
 //endpoint
    val ENDPOINT_LOGIN = "user/login"

fun apiLogin(username: String, password: String, apiCallbackHelper: ApiCallbackHelper) {

            val hashMap = HashMap<String, String>()
            hashMap.put(KEY_USER_NAME, username)
            hashMap.put(KEY_PASSWORD, password)
            // Just Change call type and end point 
            getApiCallObservable(CALL_TYPE_POST, ENDPOINT_LOGIN, hashMap).subscribe(apiCallbackHelper) 
    }

```
Add more method as you need.


# Api Call from view model: 
```javascript
public fun fetchLogin(username: String, password: String, lifecycleOwner: LifecycleOwner) {
        dataManager.apiHelper.apiLogin(username, password, ApiCallbackHelper(livedata(lifecycleOwner,"login")))
    }
```


