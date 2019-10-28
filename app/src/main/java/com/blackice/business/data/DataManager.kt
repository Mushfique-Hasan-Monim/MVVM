package com.blackice.business.data

import android.content.Context
import com.blackice.business.data.local_db.RoomHelper
import com.blackice.business.data.network.ApiHelper
import com.blackice.business.data.network.RetrofitFactory
import com.blackice.business.data.prefence.PreferencesHelper

class DataManager(
    context: Context
) : IDataManager {
    private val context = context

    val mContext = context
    val mPref = PreferencesHelper(mContext)
    val roomHelper = RoomHelper(context)
    val apiHelper = ApiHelper(RetrofitFactory.providePostApi(mPref.prefGetToken()))

}