package com.example.oprestador.user.data

import android.util.Log

class UserFakeDataSource : UserDataSource {
    override fun update(callback: ProfileCallback) {
        Log.i("Update", "Update Profile")
    }
}