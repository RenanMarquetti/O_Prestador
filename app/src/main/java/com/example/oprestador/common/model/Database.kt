package com.example.oprestador.common.model

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val pedidosList = hashSetOf<Pedido>()

    var sessionAuth : UserAuth? = null
    var sessionProfile: UserProfile? = null

    fun addUser(email: String, password: String) = usersAuth.add(UserAuth(UUID.randomUUID().toString(), email, password))
    fun addPedido(uuidDono: String, titulo: String, descricao: String, local: Address, prazo: Date, valor: String, preco: Int) {
        pedidosList.add(Pedido(uuidDono, titulo, descricao, local, prazo, BigDecimal(valor), preco))
    }

    init {

        addUser("renan@gmail.com","12345678")

        val uuidFirstUser = usersAuth.first().uuid
        val dateNow = Date()

        addPedido(uuidFirstUser,"Teste Título 1", "Teste Descrição 1", Address("Rua do Fulano", "123", "Ponta Grossa", "Contorno"), dateNow, "10.99", 420)
        addPedido(uuidFirstUser,"Teste Título 2",  "Teste Descrição 2", Address("Rua do Beltrano", "321", "Ponta Grossa", "Contorno"), dateNow, "10.99", 236)
        addPedido(uuidFirstUser,"Teste Título 3",  "Teste Descrição 3", Address("Rua Tal", "234", "Ponta Grossa", "Contorno"), dateNow, "10.99", 142)
        addPedido(uuidFirstUser, "Teste Título 4", "Teste Descrição 4", Address("Rua do morador", "432", "Ponta Grossa", "Contorno"), dateNow, "10.99", 371)

    }
}