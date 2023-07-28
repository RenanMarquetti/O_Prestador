package com.example.oprestador.user

import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView
import com.example.oprestador.common.model.Pedido

interface MeusPedidos {

    interface Presenter : BasePresenter {
        fun getMeusPedidos()
    }

    interface View : BaseView<Presenter> {
        fun setAdapter(pedidos : List<Pedido>)

        fun showFailure(msg: String)
    }
}