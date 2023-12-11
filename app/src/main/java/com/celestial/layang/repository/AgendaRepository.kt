package com.celestial.layang.repository

import android.util.Log
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.model.AgendaItem
import com.celestial.layang.model.AgendaRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AgendaRepository {
    private var apiService: ApiService = ApiClient.apiService

    suspend fun getAgendaByKelurahanId(kelurahan_id: String): List<AgendaItem> {
        return try {
            Log.e("id kelurahan dari repo", kelurahan_id)

            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val response = apiService.getAgendaList(AgendaRequest(kelurahan_id))
                if (response.isSuccessful) {
                    // Return the list of agenda items
                    response.body()?.data ?: emptyList()
                } else {
                    // Handle unsuccessful response
                    // You might want to log the error or throw an exception
                    val errorMessage = "API request failed: ${response.message()}"
                    Log.e("ApiException", errorMessage)
                    throw ApiException(errorMessage)
                }
            } // Menunggu hasil async dan mengembalikan hasilnya
        } catch (e: Exception) {
            // Handle exceptions
            // You might want to log the error or throw an exception
            throw ApiException("Error during API request: ${e.message}")
        }
    }

}

class ApiException(message: String) : Exception(message)
