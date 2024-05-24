package com.example.domain.useCase.logout

import com.example.domain.repository.IMovieRepository

class LogoutUserCase(val repository: IMovieRepository) : ILogoutUserCase {
    override suspend fun execute() {
        return repository.resetLogin()
    }

}