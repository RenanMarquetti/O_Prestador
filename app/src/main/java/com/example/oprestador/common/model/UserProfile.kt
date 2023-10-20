package com.example.oprestador.common.model

data class UserProfile (
    val uuid: String,
    val email: String? = null,
    var name: String = "",
    val telefone: Fone = Fone(),
    val endereco: Address = Address(),
    var moedas: Int = 0
)
