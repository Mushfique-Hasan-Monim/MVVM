package monim.blackice.business.data.prefence

import monim.blackice.business.data.model.user.User

interface IPreferenceHelper {
    fun prefGetCurrentUser(): User
    fun prefLogin(user: User)
    fun prefGetToken(): String
    fun prefLogout()
    fun prefGetLoginMode(): Boolean
}