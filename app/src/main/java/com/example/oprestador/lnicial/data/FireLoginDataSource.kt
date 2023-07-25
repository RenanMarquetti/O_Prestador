package com.example.oprestador.lnicial.data

import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class FireLoginDataSource : LoginDataSource  {
    override fun login(email: String, password: String, callback: LoginCallback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { res ->
                if (res != null) trazerDadosUser(res.user!!.uid, callback)
                else callback.onFailure("Usuario Não Encontrado")
            }
            .addOnFailureListener{ exception ->
                callback.onFailure(exception.message ?: "Usuario Não Encontrado")
            }

    }

    fun trazerDadosUser(uid: String, callback: LoginCallback) {

        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(uid)
            .get()
            .addOnSuccessListener { doc ->
                if(!doc.exists()) callback.onFailure("Erro ao trazer dados do usuario")
                else {
                    Database.sessionUser = doc.toObject(User::class.java)
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
}