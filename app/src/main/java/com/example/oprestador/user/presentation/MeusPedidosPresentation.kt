package com.example.oprestador.user.presentation

import com.example.oprestador.common.base.TypeCallback
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.MeusPedidos
import com.example.oprestador.user.data.UserRepository

class MeusPedidosPresentation(private var view: MeusPedidos.View?, private val repository: UserRepository) : MeusPedidos.Presenter {

    override fun getMeusPedidos() {
        repository.getMeusPedidos(object : TypeCallback<List<Pedido>> {
            override fun onSuccess(pedidos: List<Pedido>) {
                view?.setAdapter(pedidos)
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