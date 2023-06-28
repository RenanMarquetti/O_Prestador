package com.example.oprestador.user.data

interface UserDataSource {
    fun updateProfile(callback: ProfileCallback)
}