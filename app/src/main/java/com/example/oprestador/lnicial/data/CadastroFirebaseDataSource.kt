package com.example.oprestador.lnicial.data

import com.example.oprestador.R
import com.google.firebase.firestore.FirebaseFirestore

class CadastroFirebaseDataSource : CadastroDataSource {
    override fun create(email: String, password: String, callback: CadastroCallback) {

        if (existUser(email, callback)) callback.onFailure("usuario já cadastrado")
        else registerNewUser(email, password, callback)

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

    private fun registerNewUser(email: String, password: String, callback: CadastroCallback) {

    }

}