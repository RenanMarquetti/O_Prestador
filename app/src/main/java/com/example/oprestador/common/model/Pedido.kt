package com.example.oprestador.common.model

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

data class Pedido(
    val uuidDono: UUID,
    val titulo: String,
    val descricao: String,
    val endereco: Address,
    val prazo: Date,
    val valorServico: BigDecimal,
    val valorAnuncio: Int
)

