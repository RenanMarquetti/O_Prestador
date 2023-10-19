package com.example.oprestador.cliente.data

import android.os.Handler
import android.os.Looper
import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.base.TypeCallback
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido

class PedidoNovoFakeDataSource() : ClienteDataSource {
    override fun gravarPedido(pedido: Pedido, callback: DefaultCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            Database.pedidosList.add(pedido)

            callback.onSuccess()

            callback.onComplete()

        },2000)
    }

    override fun getMeusPedidos(callback: TypeCallback<List<Pedido>>) {
        TODO("Not yet implemented")
    }

}