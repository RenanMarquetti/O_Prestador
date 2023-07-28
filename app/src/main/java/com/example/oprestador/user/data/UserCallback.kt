package com.example.oprestador.user.data

interface UserCallback {
    fun onSuccess()
    fun onFailure(msg: String)
    fun onComplete()
}