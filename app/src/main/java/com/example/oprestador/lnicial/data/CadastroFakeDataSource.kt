package com.example.oprestador.lnicial.data

import android.os.Handler
import android.os.Looper
import com.example.oprestador.common.model.Database

class CadastroFakeDataSource : CadastroDataSource {

    override fun create(email: String, password: String, callback: CadastroCallback) {

        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull{
                email == it.email
            }

            if(userAuth != null)  {
                callback.onFailure("Usuário já cadastrado")
            } else {

                val newUse = Database.addUser(email, password)
                Database.sessionAuth = newUse
                callback.onSuccess(newUse)

            }

            callback.onComplete()

        },2000)

    }

}