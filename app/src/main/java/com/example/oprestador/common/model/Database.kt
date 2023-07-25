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

    fun addPedido(uuidDono: String, nomeDono: String, titulo: String, descricao: String, local: Address, prazo: Date, valor: String, preco: Int) {
        pedidosList.add(Pedido(uuidDono, nomeDono, titulo, descricao, local, prazo, BigDecimal(valor), preco))
    }

    /*init {

        val userA = addUser("renan@gmail.com")
        userA.moedas = 1000
        val userAnome = userA.profile.name
        val dateNow = Date()

        addPedido(userA.uuid,userAnome, "Teste Título 1", "Teste Descrição 1", Address("Rua do Fulano", "123", "Ponta Grossa", "Contorno"), dateNow, "10.99", 420)
        addPedido(userA.uuid,userAnome, "Teste Título 2",  "Teste Descrição 2", Address("Rua do Beltrano", "321", "Ponta Grossa", "Contorno"), dateNow, "10.99", 236)
        addPedido(userA.uuid,userAnome, "Teste Título 3",  "Teste Descrição 3", Address("Rua Tal", "234", "Ponta Grossa", "Contorno"), dateNow, "10.99", 142)
        addPedido(userA.uuid,userAnome,  "Teste Título 4", "Teste Descrição 4", Address("Rua do morador", "432", "Ponta Grossa", "Contorno"), dateNow, "10.99", 371)

    }*/
}