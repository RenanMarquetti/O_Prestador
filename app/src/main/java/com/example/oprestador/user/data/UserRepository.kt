package com.example.oprestador.user.data

class UserRepository(private val dataSource: UserDataSource) {

    fun updateProfile(callback: ProfileCallback) {
        dataSource.updateProfile(callback)
    }
}