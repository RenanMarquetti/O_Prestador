package com.example.oprestador.common.model

import java.util.UUID

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    var sessionAuth : UserAuth? = null

    fun addUser(email: String, passord: String) : UserAuth {

        val newUser = UserAuth(UUID.randomUUID().toString(), email, passord)
        usersAuth.add(newUser)
        return newUser
    }

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "renan@gmail.com","12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "jaque@gmail.com","87654321"))
    }
}