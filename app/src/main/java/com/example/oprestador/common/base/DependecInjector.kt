package com.example.oprestador.common.base

import com.example.oprestador.cliente.data.FireClienteDataSource
import com.example.oprestador.cliente.data.ClienteRepository
import com.example.oprestador.lnicial.data.InicialFireDataSource
import com.example.oprestador.lnicial.data.InicialRepository
import com.example.oprestador.lnicial.data.InicialRetrofitDataSource
import com.example.oprestador.user.data.FireUserDataSource
import com.example.oprestador.user.data.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependecInjector {
    fun inicialRepository() : InicialRepository {
        return InicialRepository(InicialRetrofitDataSource())
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

    fun retrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.106:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}