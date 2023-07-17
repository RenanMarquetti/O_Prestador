package com.example.oprestador.cliente.presentation

import com.example.oprestador.cliente.PedidoNovo
import com.example.oprestador.cliente.data.PedidoNovoCallback
import com.example.oprestador.cliente.data.PedidoNovoRepository
import com.example.oprestador.common.model.Address
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.lnicial.data.LoginRepository
import java.util.Date
import java.util.UUID

class PedidoNovoPresentation(private var view: PedidoNovo.View?, private val repository: PedidoNovoRepository) : PedidoNovo.Presenter {

    override fun createNewPedido(dados: DadosPedido) {

        var isDadosValid = true

        with(dados) {
            if(categoria.isEmpty()) isDadosValid = false
            if(subCategoria.isEmpty()) isDadosValid = false
            if(provincia.isEmpty()) isDadosValid = false
            if(cidade.isEmpty()) isDadosValid = false
            if(bairro.isEmpty()) isDadosValid = false
            if(rua.isEmpty()) isDadosValid = false
            if(numeroRua.isEmpty()) isDadosValid = false
            if(titulo.isEmpty()) isDadosValid = false
            if(descricao.isEmpty()) isDadosValid = false

            if(isDadosValid) {

                view?.showProgess(true)

                val address = Address(rua, numeroRua, cidade, bairro)
                val uuid = Database.sessionAuth!!.uuid
                val nomeCliente = Database.sessionAuth!!.profile.name

                val novoPedido = Pedido(uuid, nomeCliente, titulo, descricao, address, Date(),valor, 300)

                repository.salvarNovoPedido(novoPedido, object : PedidoNovoCallback {
                    override fun onSuccess(pedido: Pedido) {
                        view?.createDone(pedido)
                    }

                    override fun onFailure(msg: String) {
                        view?.createFailure(msg)
                    }

                    override fun onComplete() {
                        view?.showProgess(false)
                    }
                })
            } else {
                view?.inputError("O novo pedido contem algum campo vazio")
            }

        }

    }

    override fun onDestroy() {
        view = null
    }

}