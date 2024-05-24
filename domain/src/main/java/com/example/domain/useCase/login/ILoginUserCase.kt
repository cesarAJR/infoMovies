package com.example.domain.useCase.login

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow
import com.example.domain.core.Result
interface ILoginUserCase {
    suspend fun execute(email:String, password:String): Flow<Result<User>>
}