package com.example.oprestador.user

import com.example.oprestador.common.model.Pedido

interface FragmentAttachListener {
    fun goToPedido(pedido: Pedido)
}