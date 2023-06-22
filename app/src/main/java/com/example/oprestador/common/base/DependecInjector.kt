package com.example.oprestador.common.base

import com.example.oprestador.lnicial.data.FakeDataSource
import com.example.oprestador.lnicial.data.LoginRepository

object DependecInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }
}