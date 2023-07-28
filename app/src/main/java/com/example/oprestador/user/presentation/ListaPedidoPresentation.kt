package com.example.oprestador.user.presentation

import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.ListaPedido
import com.example.oprestador.user.data.ListaPedidoCallback
import com.example.oprestador.user.data.UserRepository

class ListaPedidoPresentation(private var view: ListaPedido.View?, private val repository: UserRepository) : ListaPedido.Presenter {

    override fun getListFeed() {
        repository.getFeedPedidos(object : ListaPedidoCallback {
            override fun onSuccess(pedidos: List<Pedido>) {
                view?.setAdapter(pedidos)
            }

            override fun onFailure(msg: String) {
                view?.inputError(msg)
            }

            override fun onComplete() {

            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}