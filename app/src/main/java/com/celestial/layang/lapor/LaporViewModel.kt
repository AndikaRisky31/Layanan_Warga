package com.celestial.layang.lapor

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celestial.layang.api.ApiClient.apiService
import com.celestial.layang.model.LaporanData
import com.celestial.layang.model.LaporanRequest
import com.celestial.layang.model.LaporanResponse
import com.celestial.layang.repository.LaporRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LaporViewModel(
    private val laporRepository: LaporRepository
) : ViewModel() {


    fun saveLaporan(laporanData: LaporanData) {
        val laporanRequest = LaporanRequest(laporanData)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: Response<LaporanResponse> = apiService.saveLaporan(laporanRequest)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.e(
                            "Create Laporan",
                            "Create successful - ${response.body()?.data.toString()}"
                        )
                    } else {
                        Log.e("Create Laporan", "Create unsuccessful - ${response.code()}")
                        // Handle unsuccessful response
                        // You can log the error or perform appropriate actions
                    }
                }
            } catch (t: Throwable) {
                Log.e("Create Laporan", "Failure: ${t.message}")
                // Handle failure appropriately, e.g., retrying the request or showing an error message
            }
        }
    }
}