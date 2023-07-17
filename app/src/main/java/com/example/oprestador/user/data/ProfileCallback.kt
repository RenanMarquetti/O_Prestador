package com.example.oprestador.user.data

import com.example.oprestador.common.model.UserAuth

interface ProfileCallback {
    fun onSuccess(user: UserAuth)
    fun onFailure(msg: String)
    fun onComplete()
}