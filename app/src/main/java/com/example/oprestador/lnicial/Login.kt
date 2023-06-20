package com.example.oprestador.lnicial

import com.example.oprestador.common.base.BasePresenter

interface Login {

    interface Presenter : BasePresenter {
        fun login(loginEmail: String, password: String)
    }

    interface View {
        fun showProgeess(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized()
    }
}