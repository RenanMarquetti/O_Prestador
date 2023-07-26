package com.example.oprestador.cliente.presentation

import com.example.oprestador.cliente.PedidosAbertos
import com.example.oprestador.cliente.data.ClienteRepository
import com.example.oprestador.cliente.data.PedidosAbertosCallback
import com.example.oprestador.common.model.Pedido

class PedidosAbertosPresentation(private var view: PedidosAbertos.View?, private val repository: ClienteRepository) : PedidosAbertos.Presenter {

    override fun fetchPedidos() {
        repository.fetchMeusPedidos(object : PedidosAbertosCallback<List<Pedido>> {
            override fun onSuccess(pedidos: List<Pedido>) {
                view?.insertAdapter(pedidos)
            }

            override fun onFailure(msg: String) {
                view?.createFailure(msg)
            }

            override fun onComplete() {
                view?.showProgess(false)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}