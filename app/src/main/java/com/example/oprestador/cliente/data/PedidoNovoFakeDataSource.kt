package com.example.oprestador.cliente.data

import android.os.Handler
import android.os.Looper
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido

class PedidoNovoFakeDataSource() : ClienteDataSource {
    override fun gravarPedido(pedido: Pedido, callback: PedidoNovoCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            Database.pedidosList.add(pedido)

            callback.onSuccess()

            callback.onComplete()

        },2000)
    }

    override fun getMeusPedidos(callback: PedidosAbertosCallback<List<Pedido>>) {
        TODO("Not yet implemented")
    }

}