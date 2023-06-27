package com.example.oprestador.user.presentation

import com.example.oprestador.common.model.UserProfile
import com.example.oprestador.user.Perfil
import com.example.oprestador.user.data.UserRepository
import com.example.oprestador.user.data.ProfileCallback

class PerfilPresentation(private var view: Perfil.View?, private val repository: UserRepository) : Perfil.Presenter {
    override fun updateProfile() {
        // valida os metodos

        repository.update(object : ProfileCallback {
            override fun onSuccess(profile: UserProfile) {

            }

            override fun onFailure(msg: String) {

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