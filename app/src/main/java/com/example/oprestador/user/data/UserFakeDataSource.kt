package com.example.oprestador.user.data

import com.example.oprestador.user.presentation.DadosProfile

class UserFakeDataSource : UserDataSource {
    override fun updateProfile(dados: DadosProfile, callback: ProfileCallback) {




        callback.onComplete()
    }
}