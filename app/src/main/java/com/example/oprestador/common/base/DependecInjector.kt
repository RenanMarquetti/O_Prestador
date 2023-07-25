package com.example.oprestador.common.base

import com.example.oprestador.cliente.data.FireClienteDataSource
import com.example.oprestador.cliente.data.ClienteRepository
import com.example.oprestador.lnicial.data.FireCadastroDataSource
import com.example.oprestador.lnicial.data.CadastroRepository
import com.example.oprestador.lnicial.data.FireLoginDataSource
import com.example.oprestador.lnicial.data.LoginRepository
import com.example.oprestador.user.data.FireUserDataSource
import com.example.oprestador.user.data.UserRepository

object DependecInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FireLoginDataSource())
    }

    fun cadastroRepository() : CadastroRepository {
      return CadastroRepository(FireCadastroDataSource())
    }

    fun userRepository() : UserRepository {
        return UserRepository(FireUserDataSource())
    }

    fun pedidoNovoRepository() : ClienteRepository {
        return ClienteRepository(FireClienteDataSource())
    }
}