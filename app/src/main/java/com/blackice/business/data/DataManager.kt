package com.blackice.business.data

import android.content.Context
import com.blackice.business.data.local_db.RoomHelper
import com.blackice.business.data.network.ApiHelper
import com.blackice.business.data.network.RetrofitFactory
import com.blackice.business.data.prefence.PreferencesHelper
import javax.inject.Inject

class DataManager constructor(
    preferencesHelper: PreferencesHelper,roomHelper: RoomHelper, apiHelper: ApiHelper
) : IDataManager {

    val mPref = preferencesHelper
    val roomHelper = roomHelper
    val apiHelper = apiHelper

}