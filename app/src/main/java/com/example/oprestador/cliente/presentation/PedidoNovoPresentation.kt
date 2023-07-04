package com.example.oprestador.cliente.presentation

import com.example.oprestador.cliente.PedidoNovo
import com.example.oprestador.cliente.data.PedidoNovoRepository
import com.example.oprestador.lnicial.data.LoginRepository

class PedidoNovoPresentation(private var view: PedidoNovo.View?, private val repository: PedidoNovoRepository) : PedidoNovo.Presenter {

    override fun createNewPedido(dados: DadosPedido) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

}