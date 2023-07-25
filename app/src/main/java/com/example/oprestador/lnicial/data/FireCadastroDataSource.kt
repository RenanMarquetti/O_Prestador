package com.example.oprestador.lnicial.data

import com.example.oprestador.common.model.Database
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.oprestador.common.model.User

class FireCadastroDataSource : CadastroDataSource {
    override fun create(email: String, password: String, callback: CadastroCallback) {

        //1 - verificar se existe
        //2 - criar a autenticação do usuario
        //3 - salvar usuario no fireStore

        existUser(email, password, callback)
        //createNewuser(email: String, password: String, callback: CadastroCallback)
        //salvarDataBase(uid)
    }

    private fun existUser(email: String, password: String, callback: CadastroCallback) {

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

    private fun createAuthuser(email: String, password: String, callback: CadastroCallback) {

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                if (result.user != null) {

                    val uid = result.user!!.uid

                    Database.sesionUid = uid
                    salvarNoFireStore(uid, email, callback)


                } else callback.onFailure("Erro ao Cadastrar novo usuario")
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro interno do servidor")
                callback.onComplete()
            }
    }

    private fun salvarNoFireStore(uid: String, email: String, callback: CadastroCallback) {

        if (uid == null) callback.onFailure("Erro interno do servidor")

        val user = User(uid,email)

        if (uid != null) {
            FirebaseFirestore.getInstance()
                .collection("/users")
                .document(uid)
                .set(user)
                .addOnSuccessListener {
                    callback.onSuccess()
                }
                .addOnFailureListener { exception ->
                    callback.onFailure(exception.message ?: "Erro interno do servidor")
                }
                .addOnCompleteListener {
                    callback.onComplete()
                }
        }
    }
}