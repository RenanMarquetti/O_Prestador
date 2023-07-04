package com.example.oprestador.cliente.presentation

import com.example.oprestador.common.model.Address
import java.math.BigDecimal

interface DadosPedido {
    val categoria: String
    val subCategoria: String
    val address: Address
    val titulo: String
    val descricao: String
    val valor: BigDecimal
}