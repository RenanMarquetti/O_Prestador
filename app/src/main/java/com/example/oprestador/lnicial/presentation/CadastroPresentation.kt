package com.example.oprestador.lnicial.presentation

import android.util.Log
import com.example.oprestador.lnicial.view.Cadastro

//falta adicionar o repsitory
class CadastroPresentation(private var view: Cadastro.View?) : Cadastro.Presenter {

    override fun createUser(email: String, password: String, repetPassword: String) {
        Log.i("Cadastro.Presenter","usuario Criado")
    }

    override fun onDestroy() {
        view = null
    }

}