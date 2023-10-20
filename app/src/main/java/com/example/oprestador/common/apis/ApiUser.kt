package com.example.oprestador.common.apis

import com.example.oprestador.common.model.UserProfile
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiUser {

    @GET("/users/uuid/{uuid}")
    suspend fun getUserByUUID(@Path("uuid") uuid: String) : UserProfile

    @POST("/users")
    suspend fun registerNewUser(user: UserProfile) : UserProfile

//    val apiServiceUser = DependecInjector.retrofit().create(ApiUser::class.java)
//    val userProfile = apiServiceUser.getUserByUUID(FirebaseAuth.getInstance().uid.toString())

}