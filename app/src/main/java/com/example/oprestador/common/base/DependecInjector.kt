package com.example.oprestador.common.base

import com.example.oprestador.cliente.data.FireClienteDataSource
import com.example.oprestador.cliente.data.ClienteRepository
import com.example.oprestador.lnicial.data.InicialFireDataSource
import com.example.oprestador.lnicial.data.InicialRepository
import com.example.oprestador.user.data.FireUserDataSource
import com.example.oprestador.user.data.UserRepository

object DependecInjector {
    fun inicialRepository() : InicialRepository {
        return InicialRepository(InicialFireDataSource())
    }

    fun userRepository() : UserRepository {
        return UserRepository(FireUserDataSource())
    }

    fun pedidoNovoRepository() : ClienteRepository {
        return ClienteRepository(FireClienteDataSource())
    }

    fun pedidosAbertosRepository() : ClienteRepository {
        return ClienteRepository(FireClienteDataSource())
    }

}