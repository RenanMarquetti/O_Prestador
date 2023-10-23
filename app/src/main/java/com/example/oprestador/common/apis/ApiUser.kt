package com.example.oprestador.common.apis

import com.example.oprestador.common.model.UserProfile
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiUser {

    @GET("/users/uuid/{uuid}")
    fun getUserByUUID(@Path("uuid") uuid: String) : Call<UserProfile>

    @POST("/users")
    fun registerNewUser(@Body user: UserProfile) : Call<UserProfile>

}