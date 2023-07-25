package com.example.oprestador.user.data

import com.example.oprestador.common.model.User

interface ProfileCallback {
    fun onSuccess()
    fun onFailure(msg: String)
    fun onComplete()
}