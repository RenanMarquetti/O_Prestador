package com.example.oprestador.lnicial.data

import android.os.Looper
import android.os.Handler
import com.example.oprestador.common.model.Database


class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull{
                email == it.email
            }

            when{
                userAuth == null -> callback.onFailure("Usuário não encontrado")
                userAuth.password != password -> callback.onFailure("Senha Incorreta")
                else -> {
                    Database.sessionAuth = userAuth
                    callback.onSuccess(userAuth)
                }
            }

            callback.onComplete()

        },2000)
    }
}