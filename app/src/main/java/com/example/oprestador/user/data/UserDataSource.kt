package com.example.oprestador.user.data

import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.presentation.DadosProfile

interface UserDataSource {
    fun updateProfile(dados: DadosProfile, callback: UserCallback)

    fun getFeedPedidos(callback: ListaPedidoCallback)
    fun adicionarCompraPedido(pedido: Pedido, callback: UserCallback)

    fun getMeusPedidos(callback: ListaPedidoCallback)
}