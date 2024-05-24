package com.example.data.datasource.remote.login

import com.example.prueba_softtek.data.model.MovieUpComingResponse
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class LoginRemoteDataSource(private val auth: FirebaseAuth):ILoginRemoteDataSource {
    override suspend fun login(email: String, password: String): AuthResult? {
            val result = auth.signInWithEmailAndPassword(email,password).await()
            return result
    }

}