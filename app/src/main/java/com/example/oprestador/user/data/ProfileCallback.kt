package com.example.oprestador.user.data

import com.example.oprestador.common.model.UserProfile

interface ProfileCallback {
    fun onSuccess(profile: UserProfile)
    fun onFailure(msg: String)
    fun onComplete()
}