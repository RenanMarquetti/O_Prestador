package com.example.oprestador.lnicial

import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView

interface Login {

    interface Presenter : BasePresenter {
        fun login(loginEmail: String, password: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgeess(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message : String)
    }
}