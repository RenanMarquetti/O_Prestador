package com.example.oprestador.user.presentation

import com.example.oprestador.user.ListaPedido
import com.example.oprestador.user.data.UserRepository

class ListaPedidoPresentation(private var view: ListaPedido.View?, private val repository: UserRepository) : ListaPedido.Presenter {

    override fun onDestroy() {
        view = null
    }
}