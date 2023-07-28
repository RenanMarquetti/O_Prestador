package com.example.oprestador.user.presentation

import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.PedidoDetalhado
import com.example.oprestador.user.data.UserCallback
import com.example.oprestador.user.data.UserRepository
import java.util.Objects

class PedidoDetalhadoPresentation(private var view: PedidoDetalhado.View?, private val repository: UserRepository) : PedidoDetalhado.Presenter {

    override fun comprarPedido(pedido: Pedido) {
        repository.adicionarCompraPedido(pedido, object : UserCallback {
            override fun onSuccess() {
                view?.navigateToFragmentListaPedidos()
            }

            override fun onFailure(msg: String) {
                view?.showFailure(msg)
            }

            override fun onComplete() {

            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}