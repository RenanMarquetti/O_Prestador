package com.example.oprestador.common.model

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

object Database {

    val usersAuth = hashSetOf<User>()
    val pedidosList = hashSetOf<Pedido>()

    var sessionUser : User? = null
    var sesionUid = ""

    fun addUser(email: String) : User {

        val newUser = User(UUID.randomUUID().toString(), email)
        usersAuth.add(newUser)
        return newUser
    }
}