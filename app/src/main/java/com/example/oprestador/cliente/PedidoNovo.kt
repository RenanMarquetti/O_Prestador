package com.example.oprestador.cliente

import com.example.oprestador.cliente.presentation.DadosPedido
import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView
import com.example.oprestador.common.model.Pedido

interface PedidoNovo {

    interface Presenter : BasePresenter {
        fun createNewPedido(dados: DadosPedido)
    }

    interface View : BaseView<Presenter> {
        fun showProgess(enabled: Boolean)
        fun createDone()
        fun createFailure(msg: String)
        fun inputError(msg: String)
        fun limparInputs()
    }

}