package com.example.oprestador.cliente.testeretrofit

import com.example.oprestador.common.model.UserProfile
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiUser {

    @GET("/users/uuid/{uuid}")
    suspend fun getUserByUUID(@Path("uuid") uuid: String) : UserProfile

    @POST("/users")
    suspend fun registerNewUser(user: UserProfile) : UserProfile
}