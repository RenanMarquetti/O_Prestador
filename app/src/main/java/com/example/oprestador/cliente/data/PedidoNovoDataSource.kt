package com.example.oprestador.cliente.data

import com.example.oprestador.common.model.Pedido

interface PedidoNovoDataSource {
    fun gravarPedido(pedido: Pedido, callback: PedidoNovoCallback)
}