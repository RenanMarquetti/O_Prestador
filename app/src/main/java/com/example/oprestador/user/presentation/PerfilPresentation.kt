package com.example.oprestador.user.presentation

import com.example.oprestador.common.model.UserProfile
import com.example.oprestador.user.Perfil
import com.example.oprestador.user.data.UserRepository
import com.example.oprestador.user.data.ProfileCallback

class PerfilPresentation(private var view: Perfil.View?, private val repository: UserRepository) : Perfil.Presenter {
    override fun updateProfile(dados: Any) {

        // valida os dados

        repository.updateProfile(object : ProfileCallback {
            override fun onSuccess(profile: UserProfile) {
                view?.updateDone(profile)
            }

            override fun onFailure(msg: String) {
                view?.updateFailure(msg)
            }

            override fun onComplete() {
                view?.showProgess(false)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }

}