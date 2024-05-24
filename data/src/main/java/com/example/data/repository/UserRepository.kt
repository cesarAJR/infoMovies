package com.example.prueba_softtek.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.data.datasource.remote.login.ILoginRemoteDataSource
import com.example.data.datasource.remote.login.LoginRemoteDataSource
import com.example.domain.repository.IUserRepository
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.domain.core.Result
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

class UserRepository (private val loginRemoteDataSource: ILoginRemoteDataSource) : IUserRepository {
    override suspend fun login(email: String, password: String): Flow<Result<User>> = flow{
        try {
            val result = loginRemoteDataSource.login(email,password)
            val user = User(id = "1", name = result!!.user?.email?:"-")
            emit(Result.Successfull(user))
        }catch (e:Exception){
            emit(Result.Error(e.message?:""))
        }
    }

}