package com.celestial.layang.repository
import android.content.SharedPreferences
import com.celestial.layang.model.UserData
import kotlin.reflect.KProperty

class UserPreferences(private val preferences: SharedPreferences) {

    // Define the property delegate for lazy initialization
    operator fun getValue(thisRef: Any?, property: KProperty<*>): UserPreferences {
        return this
    }

    // Save user data
    fun saveUserData(userData: UserData) {
        with(preferences.edit()) {
            putInt(USER_ID_KEY, userData.userId)
            putInt(KELURAHAN_ID_KEY, userData.kelurahanId)
            putString(USERNAME_KEY, userData.username)
            putString(EMAIL_KEY, userData.email)
            putString(ALAMAT_KEY, userData.alamat)
            apply()
        }
    }

    // Retrieve user data
    fun getUserData(): UserData {
        return UserData(
            preferences.getInt(USER_ID_KEY, 0),
            preferences.getInt(KELURAHAN_ID_KEY, 0),
            preferences.getString(USERNAME_KEY, "") ?: "",
            preferences.getString(EMAIL_KEY, "") ?: "",
            preferences.getString(ALAMAT_KEY, "") ?: ""
        )
    }

    // Other methods and constants...

    companion object {
        private const val USER_ID_KEY = "user_id"
        private const val KELURAHAN_ID_KEY = "kelurahan_id"
        private const val USERNAME_KEY = "username"
        private const val EMAIL_KEY = "email"
        private const val ALAMAT_KEY = "alamat"
    }
}