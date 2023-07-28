package com.example.oprestador.user

import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView
import com.example.oprestador.common.model.Pedido

interface PedidoDetalhado {

    interface Presenter : BasePresenter {
        fun comprarPedido(pedido: Pedido)
    }

    interface View : BaseView<Presenter> {

        fun showFailure(msg: String)

        fun navigateToFragmentListaPedidos()
    }
}