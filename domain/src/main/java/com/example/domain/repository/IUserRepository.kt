package com.example.domain.repository

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow
import com.example.domain.core.Result
interface IUserRepository {

    suspend fun login(email:String,password:String):Flow<Result<User>>

}