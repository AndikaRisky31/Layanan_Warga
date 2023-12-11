package com.celestial.layang.model

import retrofit2.http.Field

data class User(
    val username:String,
    val userpassword:String,
    val email:String
)
data class ResponseObject(
    val success: Boolean,
    val message: String
)
data class LoginResponse(
    val token:String,
    val userId:String
)
data class LoginRequest(
    val username: String,
    val userPassword: String,
)
