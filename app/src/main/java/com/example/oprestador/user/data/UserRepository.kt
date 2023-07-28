package com.example.oprestador.user.data

import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.presentation.DadosProfile

class UserRepository(private val dataSource: UserDataSource) {

    fun updateProfile(dados: DadosProfile, callback: UserCallback) {
        dataSource.updateProfile(dados, callback)
    }
    fun getFeedPedidos(callback: ListaPedidoCallback) {
        dataSource.getFeedPedidos(callback)
    }

    fun adicionarCompraPedido(pedido: Pedido, callback: UserCallback) {
        dataSource.adicionarCompraPedido(pedido, callback)
    }

    fun getMeusPedidos(callback: ListaPedidoCallback) {
        dataSource.getMeusPedidos(callback)
    }
}