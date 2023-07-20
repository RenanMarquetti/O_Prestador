package com.example.oprestador.lnicial.data

interface CadastroDataSource {
    fun create(email: String, password: String, callback: CadastroCallback)
}