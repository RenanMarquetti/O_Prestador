package com.example.oprestador.cliente.data

import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.base.TypeCallback
import com.example.oprestador.common.model.Pedido
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class FireClienteDataSource : ClienteDataSource {
    override fun gravarPedido(pedido: Pedido, callback: DefaultCallback) {
        FirebaseAuth.getInstance().uid.let {
            FirebaseFirestore.getInstance()
                .collection("/pedidos")
                .document(it!!)
                .collection("pedidos_gerados")
                .document(pedido.id!!)
                .set(pedido)
                .addOnSuccessListener {
                    gravarNoFeed(pedido, callback)
                }
                .addOnFailureListener { exception ->
                    callback.onFailure(exception.message ?: "Erro ao gravar o pedido")
                }
        }
    }

    fun gravarNoFeed(pedido: Pedido, callback: DefaultCallback) {

        FirebaseFirestore.getInstance()
            .collection("/feed")
            .document(pedido.id!!)
            .set(pedido)
            .addOnSuccessListener {
                callback.onSuccess()
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao gravar o pedido no feed")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }

    override fun getMeusPedidos(callback: TypeCallback<List<Pedido>>) {
        FirebaseAuth.getInstance().uid.let {
            FirebaseFirestore.getInstance()
                .collection("/pedidos")
                .document(it!!)
                .collection("pedidos_gerados")
                .get()
                .addOnSuccessListener { docs ->
                    val pedidos = mutableListOf<Pedido>()

                    for (doc in docs) {
                        pedidos.add(doc.toObject(Pedido::class.java))
                    }

                    callback.onSuccess(pedidos)
                }
                .addOnFailureListener { exception ->
                    callback.onFailure(exception.message ?: "Erro ao gravar o pedido")
                }
                .addOnCompleteListener {
                    callback.onComplete()
                }
        }
    }
}