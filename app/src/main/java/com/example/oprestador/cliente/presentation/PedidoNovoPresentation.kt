package com.example.oprestador.cliente.presentation

import com.example.oprestador.cliente.PedidoNovo
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
            if(valor.toDouble() > 0) isDadosValid = false

            if(isDadosValid) {
                val address = Address(rua, numeroRua, cidade, bairro)
                val id = UUID.randomUUID().toString()
                val nomeCliente = Database.sessionProfile!!.name

                val novoPedido = Pedido(id, nomeCliente, titulo, descricao, address, Date(),valor.toInt())

                // não a convergencia de imformação entre os pedidos da lista de pedidos que vão para o prestador e os pedidos novos
            } else {

            }

        }

    }

    override fun onDestroy() {
        view = null
    }

}