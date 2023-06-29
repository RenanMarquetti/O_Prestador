package com.example.oprestador.common.model

import java.util.UUID

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val usersProfile= hashSetOf<UserProfile>()

    var sessionAuth : UserAuth? = null
    var sessionProfile: UserProfile? = null

    fun addUser(email: String, passord: String) : UserAuth {

        val newUser = UserAuth(UUID.randomUUID().toString(), email, passord)
        usersAuth.add(newUser)
        return newUser
    }

    init {
        iniciarUser("renan@gmail.com","12345678", "Renan Marquetti",5000, "42", "988639349", "Rua Rosa Schoemberger", "523", "Ponta Grossa", "Contorno")

    }

    fun iniciarUser(email: String, password: String, name: String, moedas: Int,ddd: String, numFone: String,
                    street: String, numAddress: String, cidade: String, bairro: String) {


        val uuid = UUID.randomUUID().toString()

        val auth = UserAuth(uuid, email, password)
        val fone = Fone(ddd, numFone)
        val endereco = Address(street, numAddress, cidade, bairro)

        val userProfile = UserProfile(uuid, name, moedas, fone, endereco)

        usersAuth.add(auth)
        usersProfile.add(userProfile)
    }
}