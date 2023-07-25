package com.example.oprestador.user.data

import com.example.oprestador.common.model.User

interface ProfileCallback {
    fun onSuccess(user: User)
    fun onFailure(msg: String)
    fun onComplete()
}