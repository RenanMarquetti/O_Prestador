package com.example.oprestador.lnicial.data

import com.example.oprestador.common.base.DefaultCallback

interface InicialDataSource {
    fun login(email: String, password: String, callback: DefaultCallback)
    fun create(email: String, password: String, callback: DefaultCallback)
}