package com.example.oprestador.lnicial.presentation

import android.util.Log
import com.example.oprestador.common.model.UserAuth
import com.example.oprestador.lnicial.data.CadastroCallback
import com.example.oprestador.lnicial.data.CadastroRepository
import com.example.oprestador.lnicial.view.Cadastro

class CadastroPresentation(private var view: Cadastro.View?, private val repository: CadastroRepository) : Cadastro.Presenter {

    override fun create(email: String, password: String, repetPassword: String) {
        repository.create(email, password, repetPassword, object : CadastroCallback {
            override fun onSuccess(userAuth: UserAuth) {
                TODO("Not yet implemented")
            }

            override fun onFailure(msg: String) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onDestroy() {
        view = null
    }

}