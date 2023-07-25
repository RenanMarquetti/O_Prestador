package com.example.oprestador.common.model

data class User (
    val uuid: String? = null,
    val email: String? = null,
    val profile: UserProfile = UserProfile(),
    var moedas: Int = 0
)
