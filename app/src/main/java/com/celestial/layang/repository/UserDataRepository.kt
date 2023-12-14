package com.celestial.layang.repository

import android.content.Context
import android.util.Log
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.model.UserData
import com.celestial.layang.model.UserRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserDataRepository(context: Context) {
    private var apiService: ApiService = ApiClient.apiService
    private val userPreferences: UserPreferences by lazy {
        UserPreferences(context.getSharedPreferences("User_Data", Context.MODE_PRIVATE))
    }

    suspend fun userData(): UserData {
        val data = userPreferences.getUserData().user_id

        try {
            val response = withContext(Dispatchers.IO) {
                apiService.getProfileData(UserRequest(data))
            }

            if (response.isSuccessful) {
                // Return the profile data
                return response.body()?.data ?: throw ApiException("Profile data is null")
            } else {
                // Handle unsuccessful response
                val errorMessage = "API request failed: ${response.message()}"
                Log.e("ApiException", errorMessage)
                throw ApiException(errorMessage)
            }
        } catch (e: Exception) {
            // Handle exceptions
            Log.e("Error", "Error during API request: ${e.message}", e)
            throw ApiException("Error during API request: ${e.message}")
        }
    }

    private fun convertTime(timeString: String): LocalDate {
        // Menggunakan DateTimeFormatter untuk menguraikan string timestamp
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        return LocalDate.parse(timeString, formatter)
    }
}
