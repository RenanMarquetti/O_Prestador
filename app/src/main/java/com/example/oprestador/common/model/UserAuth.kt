package com.example.oprestador.common.model

data class UserAuth(
    val uuid: String,
    val email: String,
    val profile: UserProfile = UserProfile(),
    var moedas: Int = 0
)
