package com.example.oprestador.lnicial.view

import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView
import com.example.oprestador.common.model.UserAuth

interface Cadastro {

    interface Presenter : BasePresenter {
        fun createUser(email: String, password: String, repetPassword: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgeess(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun displayRepetPasswordFailure(repetPasswordError: Int?)
        fun onUserCreated(user: UserAuth)

    }
}