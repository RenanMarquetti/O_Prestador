package com.example.oprestador.cliente.data

import com.example.oprestador.common.model.Pedido

class PedidoNovoRepository(private val dataSource: PedidoNovoDataSource) {
    fun salvarNovoPedido(pedido: Pedido, callback: PedidoNovoCallback) {

        dataSource.gravarPedido(pedido, callback)
    }
}