package com.example.oprestador.cliente.data

import android.os.Handler
import android.os.Looper
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import java.util.Objects

class PedidoNovoFakeDataSource() : PedidoNovoDataSource {
    override fun gravarPedido(pedido: Pedido, callback: PedidoNovoCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            Database.pedidosList.add(pedido)

            callback.onSuccess(pedido)

            callback.onComplete()

        },2000)
    }

}