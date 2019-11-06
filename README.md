# MVVM
Android MVVM Project example with kotlin, Livedata, Viewmodel, Dagger

Kotlin android application example with MVVM pattern, android architecture components, and kotlin coroutine.
This sample is about article read which the API i used from [Appshill](http://nurhossen.info/appsHill/api/).
* [Kotlin](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/jetpack/arch/livedata)
* [Room](https://developer.android.com/training/data-storage/room/) // Local data storage
* [Retrofit](https://square.github.io/retrofit/) // Networking
* [GSON](https://github.com/square/moshi) // JSON parser
* [Glide](http://square.github.io/picasso/) // Image loader

Use recyler adapter like
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

BaseRecyclerAdapter constractor contain three parameter (context, IAdapterListener, and data list)
IAdapterListener has two override method
* getViewHolder() with parameter viewgroup and viewType.
  return BaseViewHolder
* clickListener() with parameter position, generic model class and clickted view

One adapter for all recycler view.
    
