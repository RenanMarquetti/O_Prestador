package com.example.oprestador.lnicial.data

import android.os.Looper
import android.os.Handler


class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            if(email == "a@a.com" && password == "12345678") callback.onSuccess()
            else callback.onFailure("Usuário não encontrado")

            callback.onComplete()

        },2000)
    }
}