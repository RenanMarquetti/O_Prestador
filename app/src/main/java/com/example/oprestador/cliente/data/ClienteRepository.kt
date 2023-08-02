package com.example.oprestador.cliente.data

import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.base.TypeCallback
import com.example.oprestador.common.model.Pedido

class ClienteRepository(private val dataSource: ClienteDataSource) {
    fun salvarNovoPedido(pedido: Pedido, callback: DefaultCallback) {

        dataSource.gravarPedido(pedido, callback)
    }

    fun fetchMeusPedidos(callback: TypeCallback<List<Pedido>>) {
        dataSource.getMeusPedidos(callback)
    }
}