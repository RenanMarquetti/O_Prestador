package com.example.oprestador.common.model

data class UserProfile(
    var name: String = "",
    val telefone: Fone = Fone(),
    val endereco: Address = Address(),
)
