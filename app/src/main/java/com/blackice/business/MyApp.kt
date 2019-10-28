package com.blackice.business

import android.app.Application
import android.content.Context
import com.blackice.business.data.DataManager

class MyApp : Application() {

    private lateinit var dataManager: DataManager

    lateinit var context: Context


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        context = base
        setDataManager()
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun getDataManager(): DataManager {
        return dataManager
    }

    fun setDataManager() {
        dataManager = DataManager(this)
    }
}
