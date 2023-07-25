package com.example.oprestador.cliente.data

import com.example.oprestador.common.model.Pedido

interface ClienteDataSource {
    fun gravarPedido(pedido: Pedido, callback: PedidoNovoCallback)

    fun getMeusPedidos(callback: PedidosAbertosCallback<List<Pedido>>)
}