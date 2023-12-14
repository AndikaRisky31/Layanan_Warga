package com.celestial.layang.model

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
data class AgendaRequest(
    val kelurahan_id: String
    )

data class AgendaResponse(
    val message: String,
    val data:List<AgendaItem>
)

