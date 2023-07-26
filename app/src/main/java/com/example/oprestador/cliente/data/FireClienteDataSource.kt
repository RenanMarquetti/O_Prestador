package com.example.oprestador.cliente.data

import com.example.oprestador.common.model.Pedido
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class FireClienteDataSource : ClienteDataSource {
    override fun gravarPedido(pedido: Pedido, callback: PedidoNovoCallback) {
        FirebaseAuth.getInstance().uid.let {
            FirebaseFirestore.getInstance()
                .collection("/pedidos")
                .document(it!!)
                .collection("pedidos")
                .document(pedido.id!!)
                .set(pedido)
                .addOnSuccessListener {
                    callback.onSuccess()
                }
                .addOnFailureListener { exception ->
                    callback.onFailure(exception.message ?: "Erro ao gravar o pedido")
                }
                .addOnCompleteListener {
                    callback.onComplete()
                }
        }
    }

    override fun getMeusPedidos(callback: PedidosAbertosCallback<List<Pedido>>) {
        FirebaseAuth.getInstance().uid.let {
            FirebaseFirestore.getInstance()
                .collection("/pedidos")
                .document(it!!)
                .collection("pedidos")
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