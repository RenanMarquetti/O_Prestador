package com.example.oprestador.lnicial.data

import com.example.oprestador.common.model.UserAuth

interface CadastroCallback {

    fun onSuccess(userAuth: UserAuth)
    fun onFailure(msg: String)
    fun onComplete()
}