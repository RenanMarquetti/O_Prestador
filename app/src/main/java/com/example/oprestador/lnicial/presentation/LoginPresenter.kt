package com.example.oprestador.lnicial.presentation

import android.util.Patterns
import com.example.oprestador.R
import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.lnicial.Login
import com.example.oprestador.lnicial.data.InicialDataSource
import com.example.oprestador.lnicial.data.InicialRepository

class LoginPresenter(private var view: Login.View?, private val repository: InicialRepository) : Login.Presenter {

    override fun login(loginEmail: String, password: String) {

        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches()
        val isPasswordValid = password.length >= 8

        if(!isEmailValid) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if(!isPasswordValid) {
            view?.displayPasswordFailure(R.string.invalid_password)
        } else {
           view?.displayPasswordFailure(null)
        }

        if (isEmailValid && isPasswordValid) {
            view?.showProgeess(true)

            repository.login(loginEmail,password, object : DefaultCallback {
                override fun onSuccess() {
                    view?.onUserAuthenticated()
                }

                override fun onFailure(msg: String) {
                    view?.onUserUnauthorized(msg)
                }

                override fun onComplete() {
                    view?.showProgeess(false)
                }

            })
        }

    }

    override fun onDestroy() {
        view = null;
    }
}