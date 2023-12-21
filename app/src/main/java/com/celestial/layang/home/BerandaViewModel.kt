package com.celestial.layang.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.model.ArticleModel
import com.celestial.layang.model.ArticleSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BerandaViewModel : ViewModel() {
    // Gunakan MutableLiveData untuk data yang dapat berubah
    private var apiService: ApiService = ApiClient.apiService
    val beritaList = MutableLiveData<List<ArticleModel>>()

    // Gunakan suspend function untuk melakukan pemanggilan API secara asynchronous
    suspend fun fetchLatestArticles() {
        withContext(Dispatchers.IO) {
            try {
                // Panggil ApiService di sini
                val response = apiService.getLatestArticles(ArticleSize(4)) // Ganti 3 dengan ukuran yang diinginkan

                if (response.isSuccessful) {
                    // Ambil data dari response.body() dan isi ke MutableLiveData
                    beritaList.postValue(response.body())
                } else {
                    // Handle kesalahan response di sini
                    // Misalnya, tampilkan pesan kesalahan atau log
                }
            } catch (e: Exception) {
                // Handle exception jika terjadi kesalahan saat melakukan request
                // Misalnya, tampilkan pesan kesalahan atau log
            }
        }
    }
}

