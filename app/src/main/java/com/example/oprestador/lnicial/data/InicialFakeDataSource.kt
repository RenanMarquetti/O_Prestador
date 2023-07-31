package com.example.oprestador.lnicial.data

import android.os.Handler
import android.os.Looper
import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.model.Database

class InicialFakeDataSource : InicialDataSource {
    override fun login(email: String, password: String, callback: DefaultCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull{
                email == it.email
            }

            when{
                userAuth == null -> callback.onFailure("Usuário não encontrado")
                //userAuth.password != password -> callback.onFailure("Senha Incorreta")
                else -> {
                    Database.sessionUser = userAuth
                    callback.onSuccess()
                }
            }

            callback.onComplete()

        },2000)
    }

    override fun create(email: String, password: String, callback: DefaultCallback) {

        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull{
                email == it.email
            }

            if(userAuth != null)  {
                callback.onFailure("Usuário já cadastrado")
            } else {

                val newUse = Database.addUser(email)
                Database.sessionUser = newUse
                callback.onSuccess()

            }

            callback.onComplete()

        },2000)

    }

}