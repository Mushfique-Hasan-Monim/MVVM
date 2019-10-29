package com.blackice.business.data.local_db.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class Article {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "localId")  public var localId:Int = 0
    @ColumnInfo(name = "id") lateinit var id: String
    @ColumnInfo(name = "author_id") lateinit var author_id: String
    @ColumnInfo(name = "category_id") lateinit var category_id: String
    @ColumnInfo(name = "title") lateinit var title: String
    @ColumnInfo(name = "description") var description: String? = null
    @ColumnInfo(name = "image") lateinit var image: String
    @ColumnInfo(name = "author_name") lateinit var author_name: String
    @ColumnInfo(name = "author_image") lateinit var author_image: String
    @ColumnInfo(name = "views") lateinit var views: String
    @ColumnInfo(name = "likes") lateinit var likes: String
    @ColumnInfo(name = "created_date") lateinit var created_date: String
    @ColumnInfo(name = "category") lateinit var category: String

}