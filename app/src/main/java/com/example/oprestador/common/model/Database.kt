package com.example.oprestador.common.model

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val pedidosList = hashSetOf<Pedido>()

    var sessionAuth : UserAuth? = null
    var sessionProfile: UserProfile? = null

    fun addUser(email: String, passord: String) : UserAuth {

        val newUser = UserAuth(UUID.randomUUID().toString(), email, passord)
        usersAuth.add(newUser)
        return newUser
    }

    fun addPedido(uuidDono: String, titulo: String, descricao: String, local: Address, prazo: Date, valor: String, preco: Int) {

        val pedido = Pedido(uuidDono, titulo, descricao, local, prazo, BigDecimal(valor), preco)
        pedidosList.add(pedido)
    }

    init {

        iniciarUser("renan@gmail.com","12345678", "Renan Marquetti",5000, "42", "988639349", "Rua Rosa Schoemberger", "523", "Ponta Grossa", "Contorno")

        addPedido("Teste Título 1", "Renan Marquetti", "Teste Descrição 1", Address("Rua do Fulano", "123", "Ponta Grossa", "Contorno"), Date(), "10.99", 420)
        addPedido("Teste Título 2", "Fulano", "Teste Descrição 2", Address("Rua do Beltrano", "321", "Ponta Grossa", "Contorno"), Date(), "10.99", 236)
        addPedido("Teste Título 3", "Ciclano", "Teste Descrição 3", Address("Rua Tal", "234", "Ponta Grossa", "Contorno"), Date(), "10.99", 142)
        addPedido("Teste Título 4", "Beltrano", "Teste Descrição 4", Address("Rua do morador", "432", "Ponta Grossa", "Contorno"), Date(), "10.99", 371)

    }

    fun iniciarUser(email: String, password: String, name: String, moedas: Int,ddd: String, numFone: String,
                    street: String, numAddress: String, cidade: String, bairro: String) {


        val uuid = UUID.randomUUID().toString()

        val auth = UserAuth(uuid, email, password)

        with(auth.profile){
            telefone.ddd = ddd
            telefone.telefone = numFone

            endereco.street = street
            endereco.numEndereco = numAddress
            endereco.city = cidade
            endereco.neighborhood = bairro

        }

        usersAuth.add(auth)
    }
}