package com.example.oprestador.cliente

import com.example.oprestador.cliente.presentation.DadosPedido
import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView

interface PedidoNovo {

    interface Presenter : BasePresenter {
        fun createNewPedido(dados: DadosPedido)
    }

    interface View : BaseView<Presenter> {

    }

}