package com.example.oprestador.cliente.data

import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.base.TypeCallback
import com.example.oprestador.common.model.Pedido

interface ClienteDataSource {
    fun gravarPedido(pedido: Pedido, callback: DefaultCallback)

    fun getMeusPedidos(callback: TypeCallback<List<Pedido>>)
}