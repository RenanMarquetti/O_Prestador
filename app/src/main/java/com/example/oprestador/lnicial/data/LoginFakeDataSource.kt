package com.example.oprestador.lnicial.data

import android.os.Looper
import android.os.Handler
import com.example.oprestador.common.model.Database


class LoginFakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull{
                email == it.email
            }

            when{
                userAuth == null -> callback.onFailure("Usuário não encontrado")
                //userAuth.password != password -> callback.onFailure("Senha Incorreta")
                else -> {
                    Database.sessionAuth = userAuth
                    callback.onSuccess()
                }
            }

            callback.onComplete()

        },2000)
    }
}