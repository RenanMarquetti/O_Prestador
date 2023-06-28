package com.example.oprestador.user.presentation

import com.example.oprestador.common.model.UserProfile
import com.example.oprestador.user.Perfil
import com.example.oprestador.user.data.UserRepository
import com.example.oprestador.user.data.ProfileCallback

class PerfilPresentation(private var view: Perfil.View?, private val repository: UserRepository) : Perfil.Presenter {
    override fun updateProfile(dados: DadosProfile) {

        var isDadosValid = true

        with(dados) {
            if(name.isEmpty()) isDadosValid = false
            if(ddd.isEmpty()) isDadosValid = false
            if(telefone.isEmpty()) isDadosValid = false
            if(street.isEmpty()) isDadosValid = false
            if(numStreet.isEmpty()) isDadosValid = false
            if(city.isEmpty()) isDadosValid = false
            if(bairro.isEmpty()) isDadosValid = false
        }

        if(isDadosValid) {

            repository.updateProfile(dados,  object : ProfileCallback {
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
        } else {
            view?.inputError("Algun dado está vazio")
        }
    }

    override fun onDestroy() {
        view = null
    }

}