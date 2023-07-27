package com.example.oprestador.user.data

import com.example.oprestador.common.model.Database
import com.example.oprestador.user.presentation.DadosProfile
import com.google.firebase.firestore.FirebaseFirestore

class FireUserDataSource : UserDataSource {

    override fun updateProfile(dados: DadosProfile, callback: ProfileCallback) {

        with(Database.sessionUser!!.profile) {
            name = dados.name
            telefone.ddd = dados.ddd
            telefone.telefone = dados.telefone
            endereco.street = dados.street
            endereco.numEndereco = dados.numStreet
            endereco.city = dados.city
            endereco.neighborhood = dados.bairro

            FirebaseFirestore.getInstance()
                .collection("/users")
                .document(Database.sessionUser!!.uuid!!)
                .update("profile", this)
                .addOnSuccessListener {
                    callback.onSuccess()
                }
                .addOnFailureListener { exception ->
                    callback.onFailure(exception.message ?: "Erro ao atualizar os dados do usuario")
                }
                .addOnCompleteListener {
                    callback.onComplete()
                }
        }
    }

    override fun getFeedPedidos() {

    }

    override fun adicionarCompraPedido() {

    }

}