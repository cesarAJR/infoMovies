package com.example.domain.useCase.login

import com.example.domain.repository.IUserRepository
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow
import com.example.domain.core.Result
class LoginUserCase(val repository: IUserRepository) : ILoginUserCase {
    override suspend fun execute(email:String, password:String): Flow<Result<User>> {
        return repository.login(email,password)
    }
}