package com.example.oprestador.common.base

import com.example.oprestador.common.model.Pedido

interface TypeCallback<T> {
    fun onSuccess(res : T)
    fun onFailure(msg: String)
    fun onComplete()
}