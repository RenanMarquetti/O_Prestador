package com.example.oprestador.user

import com.example.oprestador.common.base.BasePresenter
import com.example.oprestador.common.base.BaseView
import com.example.oprestador.common.model.UserProfile

interface Perfil {

    interface Presenter : BasePresenter {
        fun updateProfile(dados: Any)
    }

    interface View : BaseView<Presenter> {
        fun showProgess(enabled: Boolean)
        fun updateDone(profile: UserProfile)

        fun updateFailure(msg: String)
    }
}