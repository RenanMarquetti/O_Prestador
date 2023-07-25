package com.example.oprestador.lnicial

import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView

interface Cadastro {

    interface Presenter : BasePresenter {
        fun create(email: String, password: String, repetPassword: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgeess(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun displayRepetPasswordFailure(repetPasswordError: Int?)
        fun onUserCreated()
        fun onUserUnCreated(msg: String)

    }
}