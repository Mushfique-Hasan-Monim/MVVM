package com.blackice.business.data.local_db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Category {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "localId")  public var localId:Int = 0
    @ColumnInfo(name = "id") lateinit var id: String
    @ColumnInfo(name = "parent_id") lateinit var parent_id: String
    @ColumnInfo(name = "name") lateinit var name: String
    @ColumnInfo(name = "image") lateinit var image: String

}