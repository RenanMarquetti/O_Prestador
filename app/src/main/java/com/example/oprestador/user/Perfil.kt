package com.example.oprestador.user

import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView
import com.example.oprestador.common.model.UserProfile
import com.example.oprestador.user.presentation.DadosProfile

interface Perfil {

    interface Presenter : BasePresenter {
        fun updateProfile(dados: DadosProfile)
    }

    interface View : BaseView<Presenter> {
        fun showProgess(enabled: Boolean)
        fun updateDone()
        fun updateFailure(msg: String)
        fun inputError(msg: String)
    }
}