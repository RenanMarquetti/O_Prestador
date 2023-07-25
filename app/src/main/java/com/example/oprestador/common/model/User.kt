package com.example.oprestador.common.model

data class User (
    val uuid: String,
    val email: String,
    val profile: UserProfile = UserProfile(),
    var moedas: Int = 0
)
