package com.example.oprestador.user.data

class UserRepository(private val dataSource: UserDataSource) {

    fun update(callback: ProfileCallback) {
        dataSource.update(callback)
    }
}