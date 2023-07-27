package com.example.oprestador.user.presentation

import com.example.oprestador.user.MeusPedidos
import com.example.oprestador.user.data.UserRepository

class MeusPedidosPresentation(private var view: MeusPedidos.View?, private val repository: UserRepository) : MeusPedidos.Presenter {

    override fun onDestroy() {
        view = null
    }
}