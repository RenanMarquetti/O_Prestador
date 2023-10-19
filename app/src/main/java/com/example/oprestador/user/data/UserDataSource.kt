package com.example.oprestador.user.data

import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.base.TypeCallback
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.presentation.DadosProfile

interface UserDataSource {
    fun updateProfile(dados: DadosProfile, callback: DefaultCallback)

    fun getFeedPedidos(callback: TypeCallback<List<Pedido>>)
    fun adicionarCompraPedido(pedido: Pedido, callback: DefaultCallback)

    fun getMeusPedidos(callback: TypeCallback<List<Pedido>>)
}