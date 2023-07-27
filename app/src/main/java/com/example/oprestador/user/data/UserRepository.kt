package com.example.oprestador.user.data

import com.example.oprestador.user.presentation.DadosProfile

class UserRepository(private val dataSource: UserDataSource) {

    fun updateProfile(dados: DadosProfile, callback: ProfileCallback) {
        dataSource.updateProfile(dados, callback)
    }

    fun getFeedPedidos() {
        dataSource.getFeedPedidos()
    }

    fun adicionarCompraPedido() {
        dataSource.adicionarCompraPedido()
    }
}