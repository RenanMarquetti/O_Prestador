package com.example.oprestador.lnicial.data

import com.google.firebase.auth.FirebaseAuth

class FireLoginDataSource : LoginDataSource  {
    override fun login(email: String, password: String, callback: LoginCallback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { res ->
                if (res != null) callback.onSuccess()
                else callback.onFailure("Usuario Não Encontrado")
            }
            .addOnFailureListener{ exception ->
                callback.onFailure(exception.message ?: "Usuario Não Encontrado")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }
}