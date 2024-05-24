package com.example.data.datasource.remote.login

import com.example.prueba_softtek.data.model.MovieUpComingResponse
import com.google.firebase.auth.AuthResult

interface ILoginRemoteDataSource {
    suspend fun login(email: String, password: String): AuthResult?
}