package com.example.oprestador.lnicial.data

import android.util.Log

class CadastroFakeDataSource : CadastroDataSource {

    override fun create(mail: String, password: String, repetPassword: String, callback: CadastroCallback) {
        Log.i("Cadastro.Presenter","usuario Criado")
    }


}