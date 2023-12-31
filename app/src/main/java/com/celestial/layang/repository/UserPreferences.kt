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
            userData.user_id?.let { putInt(USER_ID_KEY, it) }
            userData.kelurahan_id?.let { putInt(KELURAHAN_ID_KEY, it) }
            putString(USERNAME_KEY, userData.username)
            putString(NOMOR_KEY,userData.nomor)
            putString(PASSWORD_KEY,userData.password)
            putString(EMAIL_KEY, userData.email)
            putString(ALAMAT_KEY, userData.alamat)
            putString(KOTA_KEY,userData.kota)
            putString(IMAGEURL_KEY,userData.imageURL)
            apply()
        }
    }

    // Retrieve user data
    fun getUserData(): UserData {
        return UserData(
            preferences.getInt(USER_ID_KEY, 0),
            preferences.getInt(KELURAHAN_ID_KEY, 0),
            preferences.getString(USERNAME_KEY, "") ?: "",
            preferences.getString(PASSWORD_KEY, "") ?: "",
            preferences.getString(NOMOR_KEY, "") ?: "",
            preferences.getString(EMAIL_KEY, "") ?: "",
            preferences.getString(ALAMAT_KEY,"")?: "",
            preferences.getString(KOTA_KEY,"")?: "",
            preferences.getString(IMAGEURL_KEY,"")?: "",
        )
    }
    fun deleteUserData() {
        with(preferences.edit()) {
            remove(USER_ID_KEY)
            remove(KELURAHAN_ID_KEY)
            remove(USERNAME_KEY)
            remove(NOMOR_KEY)
            remove(PASSWORD_KEY)
            remove(EMAIL_KEY)
            remove(ALAMAT_KEY)
            remove(KOTA_KEY)
            remove(IMAGEURL_KEY)
            apply()
        }
    }
    // Inside the UserPreferences class
    fun isUserDataNotEmpty(userData: UserData): Boolean {
        return userData.user_id != 0 ||
                userData.kelurahan_id != 0 ||
                userData.username!!.isNotEmpty() ||
                userData.nomor!!.isNotEmpty() ||
                userData.password!!.isNotEmpty() ||
                userData.email!!.isNotEmpty() ||
                userData.alamat!!.isNotEmpty() ||
                userData.kota!!.isNotEmpty() ||
                userData.imageURL!!.isNotEmpty()
    }

    companion object {
        private const val USER_ID_KEY = "user_id"
        private const val KELURAHAN_ID_KEY = "kelurahan_id"
        private const val USERNAME_KEY = "username"
        private const val NOMOR_KEY = "nomor"
        private const val EMAIL_KEY = "email"
        private const val ALAMAT_KEY = "alamat"
        private const val KOTA_KEY = "TempatLahir"
        private const val IMAGEURL_KEY = "imageURL"
        private const val PASSWORD_KEY = "password"
    }
}
