package com.example.oprestador.cliente.data

import com.example.oprestador.common.model.Pedido

interface PedidosAbertosCallback<T> {
    fun onSuccess(pedidos: List<Pedido>)
    fun onFailure(msg: String)
    fun onComplete()
}
