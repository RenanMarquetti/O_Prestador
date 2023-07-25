package com.example.oprestador.user.data

import com.example.oprestador.common.model.Database
import com.example.oprestador.user.presentation.DadosProfile

class UserFakeDataSource : UserDataSource {
    override fun updateProfile(dados: DadosProfile, callback: ProfileCallback) {

            with(Database.sessionUser!!.profile){
                name = dados.name
                telefone.ddd = dados.ddd
                telefone.telefone = dados.telefone
                endereco.street = dados.street
                endereco.numEndereco = dados.numStreet
                endereco.city = dados.city
                endereco.neighborhood = dados.bairro
            }

        callback.onSuccess(Database.sessionUser!!)
        callback.onComplete()
    }
}