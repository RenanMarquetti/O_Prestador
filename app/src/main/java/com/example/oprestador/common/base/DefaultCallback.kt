package com.example.oprestador.common.base

interface DefaultCallback {
    fun onSuccess()
    fun onFailure(msg: String)
    fun onComplete()
}