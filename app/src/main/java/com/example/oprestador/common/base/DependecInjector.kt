package com.example.oprestador.common.base

import com.example.oprestador.lnicial.data.CadastroFakeDataSource
import com.example.oprestador.lnicial.data.CadastroRepository
import com.example.oprestador.lnicial.data.LoginFakeDataSource
import com.example.oprestador.lnicial.data.LoginRepository
import com.example.oprestador.user.data.UserFakeDataSource
import com.example.oprestador.user.data.UserRepository

object DependecInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(LoginFakeDataSource())
    }

    fun cadastroRepository() : CadastroRepository {
      return CadastroRepository(CadastroFakeDataSource())
    }

    fun userRepository() : UserRepository {
        return UserRepository(UserFakeDataSource())
    }
}