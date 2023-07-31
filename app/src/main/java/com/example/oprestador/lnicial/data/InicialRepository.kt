package com.example.oprestador.lnicial.data

import com.example.oprestador.common.base.DefaultCallback

class InicialRepository(private val dataSource: InicialDataSource) {
    fun login(email: String, password: String, callback: DefaultCallback) {
        dataSource.login(email, password, callback)
    }
    fun create(email: String, password: String, callback: DefaultCallback) {
        dataSource.create(email, password, callback)
    }
}