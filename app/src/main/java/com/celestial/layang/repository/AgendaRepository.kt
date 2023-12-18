package com.celestial.layang.repository

import android.util.Log
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.model.AgendaItem
import com.celestial.layang.model.ApiException
import com.celestial.layang.model.KelurahanIdRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AgendaRepository {
    private var apiService: ApiService = ApiClient.apiService

    suspend fun getAgendaByKelurahanId(kelurahan_id: String): List<AgendaItem> {
        return try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val response = apiService.getAgendaList(KelurahanIdRequest(kelurahan_id))
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
}
