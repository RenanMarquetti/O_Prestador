package com.example.oprestador.cliente.presentation

import com.example.oprestador.common.model.Address
import java.math.BigDecimal

interface DadosPedido {
    val categoria: String
    val subCategoria: String
    val provincia: String
    val cidade: String
    val bairro: String
    val rua: String
    val numeroRua: String
    val titulo: String
    val descricao: String
    val valor: BigDecimal
}