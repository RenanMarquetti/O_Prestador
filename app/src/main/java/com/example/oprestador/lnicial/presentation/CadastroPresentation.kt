package com.example.oprestador.lnicial.presentation

import android.util.Log
import android.util.Patterns
import com.example.oprestador.R
import com.example.oprestador.common.model.UserAuth
import com.example.oprestador.lnicial.data.CadastroCallback
import com.example.oprestador.lnicial.data.CadastroRepository
import com.example.oprestador.lnicial.view.Cadastro

class CadastroPresentation(private var view: Cadastro.View?, private val repository: CadastroRepository) : Cadastro.Presenter {

    override fun create(loginEmail: String, password: String, repetPassword: String) {

        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches()
        val isPasswordValid = password.length >= 8
        val isRepetPassoreValid = password.equals(repetPassword)

        if(!isEmailValid) view?.displayEmailFailure(R.string.invalid_email)
        else view?.displayEmailFailure(null)

        if(!isPasswordValid) view?.displayPasswordFailure(R.string.invalid_password)
        else view?.displayPasswordFailure(null)

        if(!isRepetPassoreValid) view?.displayRepetPasswordFailure(R.string.invalid_repet_password)
        else view?.displayRepetPasswordFailure(null)

        if(isEmailValid && isPasswordValid && isRepetPassoreValid) {

            view?.showProgeess(true)

            repository.create(loginEmail, password, object : CadastroCallback {
                override fun onSuccess(userAuth: UserAuth) {
                    view?.onUserCreated(userAuth)
                }

                override fun onFailure(msg: String) {
                    view?.onUserUnCreated(msg)
                }

                override fun onComplete() {
                    view?.showProgeess(false)
                }

            })
        }

    }

    override fun onDestroy() {
        view = null
    }

}