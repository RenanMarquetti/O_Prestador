package com.example.oprestador.lnicial.presentation

import android.util.Patterns
import com.example.oprestador.R
import com.example.oprestador.lnicial.Login

class LoginPresenter(private var view: Login.View?): Login.Presenter {

    override fun login(loginEmail: String, password: String) {

        if(!Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches()) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if(password.length < 8) {
            view?.displayPasswordFailure(R.string.invalid_password)
        } else {
           view?.displayPasswordFailure(null)
        }

        view?.showProgeess(false)
    }

    override fun onDestroy() {
        view = null;
    }
}