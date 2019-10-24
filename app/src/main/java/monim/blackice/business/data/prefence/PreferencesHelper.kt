package monim.blackice.business.data.prefence

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import monim.blackice.business.data.model.user.User

class PreferencesHelper(context: Context) {
    private val PREF_KEY_IS_LOGIN = "PREF_KEY_IS_LOGIN"
    private val PREF_KEY_TOKEN = "PREF_KEY_TOKEN"
    private val PREF_KEY_USER_INFO = "PREF_KEY_USER_INFO"

    private val mPrefs: SharedPreferences = context.getSharedPreferences("preference_name", Context.MODE_PRIVATE)

    fun prefGetCurrentUser(): User {

        return Gson().fromJson(mPrefs.getString(PREF_KEY_USER_INFO, null),User::class.java)
    }

    fun prefLogin(user: User) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_LOGIN, true).apply()
        prefSetToken(user.token)
        mPrefs.edit().putString(PREF_KEY_USER_INFO, Gson().toJson(user)).apply()
    }

    fun prefLogout() {
        mPrefs.edit().putString(PREF_KEY_USER_INFO, null).apply()
        mPrefs.edit().putBoolean(PREF_KEY_IS_LOGIN, false).apply()

    }

    fun prefGetLoginMode(): Boolean {
        return mPrefs.getBoolean(PREF_KEY_IS_LOGIN, false)
    }

    fun prefGetToken(): String {
        return mPrefs.getString(PREF_KEY_TOKEN, "")!!
    }

    private fun prefSetToken(token: String) {
        mPrefs.edit().putString(PREF_KEY_TOKEN, token).apply()
    }



}