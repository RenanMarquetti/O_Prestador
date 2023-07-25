package com.example.oprestador.lnicial.data

interface LoginCallback {
    fun onSuccess()
    fun onFailure(msg: String)
    fun onComplete()
}