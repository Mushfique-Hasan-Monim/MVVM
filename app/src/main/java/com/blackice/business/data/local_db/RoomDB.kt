package com.blackice.business.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blackice.business.data.local_db.dao.CategoryDao
import com.blackice.business.data.local_db.entity.Category


@Database(entities = arrayOf(Category::class) , version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
}