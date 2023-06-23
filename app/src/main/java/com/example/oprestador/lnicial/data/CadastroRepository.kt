package com.example.oprestador.lnicial.data

class CadastroRepository(private val dataSource: CadastroDataSource) {
    fun create(email: String, password: String, repetPassword: String, callback: CadastroCallback) {
        dataSource.create(email,password,repetPassword,callback)
    }
}