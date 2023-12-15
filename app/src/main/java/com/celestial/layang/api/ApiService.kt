package com.celestial.layang.api

import com.celestial.layang.model.AgendaRequest
import com.celestial.layang.model.AgendaResponse
import com.celestial.layang.model.ArticleRequest
import com.celestial.layang.model.ArticleResponse
import com.celestial.layang.model.LoginRequest
import com.celestial.layang.model.LoginResponse
import com.celestial.layang.model.UpdateResponse
import com.celestial.layang.model.UserData
import com.celestial.layang.model.UserRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    /*
    // Menggunakan @POST untuk menandai metode POST
    @POST("users/create")
    fun addUser(@Body user: User): Call<ResponseObject>
*/
    @POST("auth/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

    @PUT("users/update")
    fun updateUser(@Body body: UserData): Call<UpdateResponse>

    @POST("users/data")
    suspend fun getProfileData(@Body request: UserRequest): Response<UpdateResponse>

    @POST("agenda/kelurahan_id")
    suspend fun getAgendaList(@Body request: AgendaRequest): Response<AgendaResponse>

    @POST("articles/latest")
    suspend fun getLatestArticles(@Body size:Int): Response<ArticleResponse>
    @POST("articles/page")
    suspend fun getArticlesByPage(@Body request: ArticleRequest): Response<ArticleResponse>

}
