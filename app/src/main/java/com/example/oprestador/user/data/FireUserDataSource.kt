package com.example.oprestador.user.data

import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.presentation.DadosProfile
import com.google.firebase.firestore.FirebaseFirestore

class FireUserDataSource : UserDataSource {

    override fun updateProfile(dados: DadosProfile, callback: UserCallback) {

        with(Database.sessionUser!!.profile) {
            name = dados.name
            telefone.ddd = dados.ddd
            telefone.telefone = dados.telefone
            endereco.street = dados.street
            endereco.numEndereco = dados.numStreet
            endereco.city = dados.city
            endereco.neighborhood = dados.bairro

            FirebaseFirestore.getInstance()
                .collection("/users")
                .document(Database.sessionUser!!.uuid!!)
                .update("profile", this)
                .addOnSuccessListener {
                    callback.onSuccess()
                }
                .addOnFailureListener { exception ->
                    callback.onFailure(exception.message ?: "Erro ao atualizar os dados do usuario")
                }
                .addOnCompleteListener {
                    callback.onComplete()
                }
        }
    }

    override fun getFeedPedidos(callback: ListaPedidoCallback) {
        FirebaseFirestore.getInstance()
            .collection("/feed")
            .get()
            .addOnSuccessListener { collection ->
                val documents = collection.documents
                val pedidos = mutableListOf<Pedido>()

                for(document in documents) {
                    val pedido = document.toObject(Pedido::class.java)
                    pedidos.add(pedido!!)
                }

                callback.onSuccess(pedidos)
            }
            .addOnFailureListener {exception ->
                callback.onFailure(exception.message ?: "Erro ao trazer o feed")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }

    override fun adicionarCompraPedido(pedido: Pedido, callback: UserCallback) {
        FirebaseFirestore.getInstance()
            .collection("/meus_pedidos")
            .document(Database.sessionUser!!.uuid!!)
            .collection(pedido.id!!)
            .document()
            .set(pedido)
            .addOnSuccessListener {
                sacarSaldo(Database.sessionUser!!.moedas-pedido.valorAnuncio, callback)
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao atualizar os dados do usuario")
            }

    }

    fun sacarSaldo(valor : Int, callback: UserCallback) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(Database.sessionUser!!.uuid!!)
            .update("moedas", Database.sessionUser!!.moedas-valor)
            .addOnSuccessListener {
                callback.onSuccess()
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao atualizar os dados do usuario")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }

}