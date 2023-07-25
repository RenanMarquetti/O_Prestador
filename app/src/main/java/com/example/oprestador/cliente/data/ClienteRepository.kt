package com.example.oprestador.cliente.data

import com.example.oprestador.common.model.Pedido

class ClienteRepository(private val dataSource: ClienteDataSource) {
    fun salvarNovoPedido(pedido: Pedido, callback: PedidoNovoCallback) {

        dataSource.gravarPedido(pedido, callback)
    }

    fun fetchMeusPedidos(callback: PedidosAbertosCallback <List<Pedido>>) {
        dataSource.getMeusPedidos(callback)
    }
}