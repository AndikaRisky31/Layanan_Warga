package com.celestial.layang.repository

import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.model.ArticleRequest

class ArtikelRepository {
    private var apiService: ApiService = ApiClient.apiService
    suspend fun getArtikel(size:Int){
        apiService.getLatestArticles(size)
    }
}