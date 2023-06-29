package com.example.oprestador.common.model

data class UserProfile(
    val uuid: String,
    var name: String,
    var moedas: Int,
    val telefone: Fone,
    val endereco: Address
)
