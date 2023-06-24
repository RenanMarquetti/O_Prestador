package com.example.oprestador.lnicial.data

interface CadastroDataSource {
    fun create(mail: String, password: String, callback: CadastroCallback)
}