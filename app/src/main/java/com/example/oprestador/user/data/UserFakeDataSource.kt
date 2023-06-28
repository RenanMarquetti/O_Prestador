package com.example.oprestador.user.data

import android.util.Log

class UserFakeDataSource : UserDataSource {
    override fun updateProfile(callback: ProfileCallback) {




        callback.onComplete()
    }
}