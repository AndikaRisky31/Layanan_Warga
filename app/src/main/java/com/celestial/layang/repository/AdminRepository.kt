package com.celestial.layang.repository

import android.util.Log
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.janjiTemu.KontakModel
import com.celestial.layang.model.ApiException
import com.celestial.layang.model.IdRequest
import com.celestial.layang.model.KelurahanIdRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdminRepository {
    private var apiService: ApiService = ApiClient.apiService

    suspend fun getAdminByKelurahanId(kelurahan_id: String): List<KontakModel> {
        return try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val response = apiService.getAdmin(KelurahanIdRequest(kelurahan_id))
                if (response.isSuccessful) {
                    response.body()?.data ?: emptyList()
                } else {
                    val errorMessage = "API request failed: ${response.message()}"
                    Log.e("ApiException", errorMessage)
                    throw ApiException(errorMessage)
                }
            }
        } catch (e: Exception) {
            throw ApiException("Error during API request: ${e.message}")
        }
    }
    suspend fun getAdminById(id:Int):KontakModel{
        return try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val response = apiService.getAdminById(IdRequest(id))
                if (response.isSuccessful) {
                    response.body()?.data?.firstOrNull() ?: throw NoSuchElementException("Data not found")
                } else {
                    val errorMessage = "API request failed: ${response.message()}"
                    Log.e("ApiException", errorMessage)
                    throw ApiException(errorMessage)
                }
            }
        } catch (e: Exception) {
            throw ApiException("Error during API request: ${e.message}")
        }
    }
}
