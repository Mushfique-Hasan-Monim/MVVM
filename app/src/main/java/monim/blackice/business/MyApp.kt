package monim.blackice.business

import android.app.Application
import android.content.Context
import monim.blackice.business.data.DataManager
import monim.blackice.business.data.local_db.RoomHelper
import monim.blackice.business.data.network.ApiHelper
import monim.blackice.business.data.network.RetrofitFactory
import monim.blackice.business.data.prefence.PreferencesHelper

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
