package com.celestial.layang.api

import com.celestial.layang.model.LoginRequest
import com.celestial.layang.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
/*
    // Menggunakan @POST untuk menandai metode POST
    @POST("users/create")
    fun addUser(@Body user: User): Call<ResponseObject>
*/
    @POST("user/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>
}
