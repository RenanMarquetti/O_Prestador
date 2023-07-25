package com.example.oprestador.lnicial.data

interface CadastroCallback {

    fun onSuccess()
    fun onFailure(msg: String)
    fun onComplete()
}