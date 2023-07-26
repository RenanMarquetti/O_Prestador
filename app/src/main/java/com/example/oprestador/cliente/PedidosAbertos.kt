package com.example.oprestador.cliente

import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView
import com.example.oprestador.common.model.Pedido

interface PedidosAbertos {

    interface Presenter : BasePresenter {
        fun fetchPedidos()
    }

    interface View : BaseView<Presenter> {
        fun showProgess(enabled: Boolean)
        fun createFailure(msg: String)

        fun insertAdapter(pedidos: List<Pedido>)
    }
}