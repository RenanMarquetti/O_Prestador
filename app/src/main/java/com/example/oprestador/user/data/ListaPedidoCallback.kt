package com.example.oprestador.user.data

import com.example.oprestador.common.model.Pedido

interface ListaPedidoCallback {
    fun onSuccess(pedidos : List<Pedido>)
    fun onFailure(msg: String)
    fun onComplete()
}