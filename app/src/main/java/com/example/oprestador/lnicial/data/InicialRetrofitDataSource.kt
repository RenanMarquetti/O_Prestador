package com.example.oprestador.lnicial.data

import android.util.Log
import com.example.oprestador.common.apis.ApiUser
import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InicialRetrofitDataSource : InicialDataSource {

    override fun login(email: String, password: String, callback: DefaultCallback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { res ->
                if (res != null) trazerDadosUser(res.user!!.uid, callback)
                else callback.onFailure("Usuario Não Encontrado")
            }
            .addOnFailureListener{ exception ->
                callback.onFailure(exception.message ?: "Usuario Não Encontrado")
                callback.onComplete()
            }
    }

    fun trazerDadosUser(uuid: String, callback: DefaultCallback) {

        val apiServiceUser = DependecInjector.retrofit().create(ApiUser::class.java)
        apiServiceUser.getUserByUUID(uuid).enqueue(object : Callback<UserProfile> {
            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {

                Database.sessionUser = response.body()
                callback.onSuccess()
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                callback.onFailure(t.message.toString())
                callback.onComplete()
            }
        })
    }

    override fun create(email: String, password: String, callback: DefaultCallback) {
        Log.i("cadastro", "criando usuario no firebase")
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                Log.i("cadastro", "usuario criado com sucesso")

                if (result.user != null) {
                    val uuid = result.user!!.uid

                    val userProfile = UserProfile(0,uuid,email)

                    createNewUserDatabase(userProfile, callback)

                } else callback.onFailure("Erro ao Cadastrar novo usuario")
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro interno do servidor")
                callback.onComplete()

            }
    }

    private fun createNewUserDatabase(userProfile: UserProfile, callback: DefaultCallback) {
        //todo: estudar definitivamente esse artigo sobre corrotinas do kotlin
        // https://jhrl.medium.com/kotlin-coroutines-uma-abordagem-diferente-para-programa%C3%A7%C3%A3o-concorrente-85b16573c2093

        val apiUser = DependecInjector.retrofit().create(ApiUser::class.java)

        apiUser.registerNewUser(userProfile).enqueue(object : Callback<UserProfile> {
            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                Database.sessionUser = response.body()
                callback.onSuccess()
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                Log.i("cadastro", "erro ao cadastrar no banco")
                callback.onFailure(t.message.toString())
                callback.onComplete()
            }

        })

    }
}

