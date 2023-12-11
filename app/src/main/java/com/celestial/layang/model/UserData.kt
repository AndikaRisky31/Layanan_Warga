package com.celestial.layang.model

data class UserData(
    val userId: Int,
    val kelurahanId:Int,
    val username:String,
    val email:String,
    val alamat:String
)

data class LoginResponse(
    val message: String,
    val data: UserData,
)
data class LoginRequest(
    val username: String,
    val userPassword: String,
)
