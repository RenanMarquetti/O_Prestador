package com.example.oprestador.common.model

import java.util.Date

data class Pedido(
    val id: String,
    val nomeCliente: String,
    val titulo: String,
    val descricao: String,
    val endereco: Address,
    val prazo: Date,
    val valor: Int
)
