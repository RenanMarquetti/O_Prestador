package com.example.oprestador.user.data

import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.base.TypeCallback
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.presentation.DadosProfile
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class FireUserDataSource : UserDataSource {

    override fun updateProfile(dados: DadosProfile, callback: DefaultCallback) {

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

    override fun getFeedPedidos(callback: TypeCallback<List<Pedido>>) {
        FirebaseFirestore.getInstance()
            .collection("/feed")
            .get()
            .addOnSuccessListener { collection ->
                val documents = collection.documents
                val pedidos = mutableListOf<Pedido>()

                for(document in documents) {
                    pedidos.add(document.toObject(Pedido::class.java)!!)
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

    override fun adicionarCompraPedido(pedido: Pedido, callback: DefaultCallback) {
        FirebaseFirestore.getInstance()
            .collection("/pedidos")
            .document(Database.sessionUser!!.uuid!!)
            .collection("pedidos_comprados")
            .document(pedido.id!!)
            .set(pedido)
            .addOnSuccessListener {
                sacarSaldo(Database.sessionUser!!.moedas-pedido.valorAnuncio, callback)
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao atualizar os dados do usuario")
            }

    }

    fun sacarSaldo(valor : Int, callback: DefaultCallback) {
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

    override fun getMeusPedidos(callback: TypeCallback<List<Pedido>>) {
        FirebaseFirestore.getInstance()
            .collection("/pedidos")
            .document(Database.sessionUser!!.uuid!!)
            .collection("pedidos_comprados")
            .get()
            .addOnSuccessListener { collection ->
                val pedidos = mutableListOf<Pedido>()

                for (document in collection.documents) {
                    val pedido = document.toObject(Pedido::class.java)
                    pedidos.add(pedido!!)
                }

                callback.onSuccess(pedidos)
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao trazer os meus pedidos comprados")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }
}