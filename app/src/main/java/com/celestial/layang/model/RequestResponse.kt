package com.celestial.layang.model

import com.celestial.layang.janjiTemu.KontakModel

data class RequestResponse(
    val data:String
)
//LOGIN
data class LoginRequest(
    val username: String,
    val password: String,
)
data class LoginResponse(
    val message: String,
    val data: UserData,
)

//UPDATE USER
data class UpdateResponse(
    val message: String,
    val data: UserData
)
data class UserRequest(
    val user_id: Int
)
//AGENDA
data class KelurahanIdRequest(
    val kelurahan_id: String
    )

data class AgendaResponse(
    val message: String,
    val data:List<AgendaItem>
)
data class ArticleRequest(
    val page:Int,
    val pageSize:Int
)

data class ArticleResponse(
    val message: String,
    val data:List<ArticleModel>
)
data class AdminResponse(
    val message: String,
    val data: List<KontakModel>
)
data class IdRequest(
    val id:Int
)

class ApiException(message: String) : Exception(message)
