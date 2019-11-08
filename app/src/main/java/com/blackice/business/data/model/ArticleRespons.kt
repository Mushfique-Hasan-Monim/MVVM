package com.blackice.business.data.model

import com.blackice.business.data.local_db.entity.Article

class ArticleRespons {

    var total_rows = 0
    lateinit var articles: List<Article>

}