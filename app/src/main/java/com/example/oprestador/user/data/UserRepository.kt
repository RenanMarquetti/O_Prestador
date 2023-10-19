package com.example.oprestador.user.data

import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.base.TypeCallback
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.presentation.DadosProfile

class UserRepository(private val dataSource: UserDataSource) {

    fun updateProfile(dados: DadosProfile, callback: DefaultCallback) {
        dataSource.updateProfile(dados, callback)
    }
    fun getFeedPedidos(callback: TypeCallback<List<Pedido>>) {
        dataSource.getFeedPedidos(callback)
    }

    fun adicionarCompraPedido(pedido: Pedido, callback: DefaultCallback) {
        dataSource.adicionarCompraPedido(pedido, callback)
    }

    fun getMeusPedidos(callback: TypeCallback<List<Pedido>>) {
        dataSource.getMeusPedidos(callback)
    }
}