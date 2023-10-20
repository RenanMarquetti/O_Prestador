package com.example.oprestador.common.model

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

object Database {

    val pedidosList = hashSetOf<Pedido>()
    var sessionUser : UserProfile? = null

}