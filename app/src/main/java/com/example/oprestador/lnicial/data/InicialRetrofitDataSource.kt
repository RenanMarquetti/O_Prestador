package com.example.oprestador.lnicial.data

import com.example.oprestador.common.apis.ApiUser
import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

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
        GlobalScope.async {
            Database.sessionUser = apiServiceUser.getUserByUUID(uuid)
            callback.onSuccess()
        }
    }

    override fun create(email: String, password: String, callback: DefaultCallback) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                if (result.user != null) {
                    val uuid = result.user!!.uid

                    val userProfile = UserProfile(uuid,email)

                    createNewUserDatabase(userProfile, callback)

                } else callback.onFailure("Erro ao Cadastrar novo usuario")
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro interno do servidor")
                callback.onComplete()

            }
    }

    private fun createNewUserDatabase(userProfile: UserProfile, callback: DefaultCallback) {
        val apiUser = DependecInjector.retrofit().create(ApiUser::class.java)

        GlobalScope.async {
            apiUser.registerNewUser(userProfile)
            callback.onSuccess()
        }
    }

}