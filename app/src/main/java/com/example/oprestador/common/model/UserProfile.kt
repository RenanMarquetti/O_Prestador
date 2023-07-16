package com.example.oprestador.common.model

data class UserProfile(
    var name: String = "",
    val telefone: Fone,
    val endereco: Address,
    val listPedidosComprados: HashSet<Pedido> = HashSet()
)
