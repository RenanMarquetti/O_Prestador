package com.example.oprestador.lnicial

interface Login {

    interface Presenter {
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