package com.example.oprestador.lnicial.data

import com.example.oprestador.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CadastroFirebaseDataSource : CadastroDataSource {
    override fun create(email: String, password: String, callback: CadastroCallback) {

        if (existUser(email, callback)) callback.onFailure("usuario já cadastrado")
        else {
            createNewuser(email, password, callback)
            callback.onSuccess()
        }

        callback.onComplete()
    }

    private fun existUser(email: String, callback: CadastroCallback) : Boolean {

        var exists = false

        FirebaseFirestore.getInstance()
            .collection("/users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { document  ->
                exists  = !document.isEmpty
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro interno do servidor")
            }

        return exists
    }

    private fun createNewuser(email: String, password: String, callback: CadastroCallback) : String {

        var uid = ""

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                uid = result.user?.uid.toString()
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro interno do servidor")
            }

        return uid
    }

}