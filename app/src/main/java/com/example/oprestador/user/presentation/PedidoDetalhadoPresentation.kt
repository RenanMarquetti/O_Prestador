package com.example.oprestador.user.presentation

import com.example.oprestador.user.MeusPedidos
import com.example.oprestador.user.PedidoDetalhado
import com.example.oprestador.user.data.UserRepository

class PedidoDetalhadoPresentation(private var view: MeusPedidos.View?, private val repository: UserRepository) : PedidoDetalhado.Presenter {

    fun comprarPedido() {
        repository.adicionarCompraPedido()
    }

    override fun onDestroy() {
        view = null
    }
}