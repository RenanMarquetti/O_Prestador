package com.example.oprestador.user.data

import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.user.presentation.DadosProfile

class UserFakeDataSource : UserDataSource {
    override fun updateProfile(dados: DadosProfile, callback: UserCallback) {

            with(Database.sessionUser!!.profile){
                name = dados.name
                telefone.ddd = dados.ddd
                telefone.telefone = dados.telefone
                endereco.street = dados.street
                endereco.numEndereco = dados.numStreet
                endereco.city = dados.city
                endereco.neighborhood = dados.bairro
            }

        callback.onSuccess()
        callback.onComplete()
    }

    override fun getFeedPedidos(callback: ListaPedidoCallback) {

    }

    override fun adicionarCompraPedido(pedido: Pedido, callback: UserCallback) {

    }

    override fun getMeusPedidos(callback: ListaPedidoCallback) {

    }
}