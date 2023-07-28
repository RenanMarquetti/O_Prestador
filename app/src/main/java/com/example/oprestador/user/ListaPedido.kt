package com.example.oprestador.user

import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.data.UserCallback

interface ListaPedido {

    interface Presenter : BasePresenter {
        fun getListFeed()
    }

    interface View : BaseView<Presenter> {
        fun inputError(msg: String)
        fun setAdapter(pedidos: List<Pedido>)
    }
}