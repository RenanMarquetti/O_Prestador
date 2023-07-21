package com.example.oprestador.lnicial.presentation

import android.util.Patterns
import com.example.oprestador.R
import com.example.oprestador.common.model.UserAuth
import com.example.oprestador.lnicial.Login
import com.example.oprestador.lnicial.data.LoginCallback
import com.example.oprestador.lnicial.data.LoginRepository

class LoginPresenter(private var view: Login.View?, private val repository: LoginRepository) : Login.Presenter {

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

            repository.login(loginEmail,password, object : LoginCallback {
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