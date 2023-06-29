package com.example.oprestador.common.model

data class UserAuth(
    val uuid: String,
    val email: String,
    var password: String
)
