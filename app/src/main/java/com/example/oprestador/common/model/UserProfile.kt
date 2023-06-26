package com.example.oprestador.common.model

data class UserProfile(
    val uuid: String,
    val name: String,
    val telefone: Fone,
    val endereco: Address
)
