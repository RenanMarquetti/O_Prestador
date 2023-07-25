package com.example.oprestador.common.model

import java.math.BigDecimal
import java.util.Date

data class Pedido(
    val uuidDono: String? = null,
    val nomeDono: String? = null,
    val titulo: String? = null,
    val descricao: String? = null,
    val endereco: Address = Address(),
    val prazo: Date? = null,
    val valorServico: String? = null,
    val valorAnuncio: Int = 0,
    val listUuidUsersQueCompraram: HashSet<String>? = null
)

