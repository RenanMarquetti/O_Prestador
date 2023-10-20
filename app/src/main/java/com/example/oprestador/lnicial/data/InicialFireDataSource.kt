package com.example.oprestador.lnicial.data

import com.example.oprestador.common.base.DefaultCallback
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Deprecated(message = "não terminado, use inicialRetrofitDataSource")
class InicialFireDataSource : InicialDataSource {
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

    fun trazerDadosUser(uid: String, callback: DefaultCallback) {

        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(uid)
            .get()
            .addOnSuccessListener { doc ->
                if(!doc.exists()) callback.onFailure("Erro ao trazer dados do usuario")
                else {
                    Database.sessionUser = doc.toObject(UserProfile::class.java)
                    callback.onSuccess()
                }
            }
            .addOnFailureListener{ exception ->
                callback.onFailure(exception.message ?: "Erro ao trazer dados do usuario")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }
    override fun create(email: String, password: String, callback: DefaultCallback) {
        existUser(email, password, callback)
    }

    private fun existUser(email: String, password: String, callback: DefaultCallback) {

        FirebaseFirestore.getInstance()
            .collection("/users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { document  ->
                if(!document.isEmpty) {
                    callback.onFailure("usuario já cadastrado")
                    callback.onComplete()
                }
                else createAuthuser(email, password, callback)
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro interno do servidor")
            }
    }

    private fun createAuthuser(email: String, password: String, callback: DefaultCallback) {

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                if (result.user != null) {

                    val uid = result.user!!.uid

                    //Database.sesionUid = uid
                    salvarNoFireStore(uid, email, callback)


                } else callback.onFailure("Erro ao Cadastrar novo usuario")
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro interno do servidor")
                callback.onComplete()

            }

    }

    private fun salvarNoFireStore(uid: String, email: String, callback: DefaultCallback) {

        if (uid == null) callback.onFailure("Erro interno do servidor")

//        val user = User(uid,email)
//
//        if (uid != null) {
//            FirebaseFirestore.getInstance()
//                .collection("/users")
//                .document(uid)
//                .set(user)
//                .addOnSuccessListener {
//                    callback.onSuccess()
//                }
//                .addOnFailureListener { exception ->
//                    callback.onFailure(exception.message ?: "Erro interno do servidor")
//                }
//                .addOnCompleteListener {
//                    callback.onComplete()
//                }
//        }
    }
}