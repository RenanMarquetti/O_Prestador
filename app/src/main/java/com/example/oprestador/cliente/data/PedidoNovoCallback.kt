package com.example.oprestador.cliente.data

import com.example.oprestador.common.model.Pedido

interface PedidoNovoCallback {
    fun onSuccess()
    fun onFailure(msg: String)
    fun onComplete()
}