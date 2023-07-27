package com.example.oprestador.user.data

import com.example.oprestador.user.presentation.DadosProfile

interface UserDataSource {
    fun updateProfile(dados: DadosProfile, callback: ProfileCallback)

    fun getFeedPedidos()
    fun adicionarCompraPedido()
}