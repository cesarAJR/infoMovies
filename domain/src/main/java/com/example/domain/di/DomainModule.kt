package com.example.data.di

import com.example.domain.useCase.getMovies.GetMoviesCase
import com.example.domain.useCase.getMovies.IGetMoviesCase
import com.example.domain.useCase.getMoviesRoom.GetMoviesRoomCase
import com.example.domain.useCase.getMoviesRoom.IGetMoviesRoomCase
import com.example.domain.useCase.login.ILoginUserCase
import com.example.domain.useCase.login.LoginUserCase
import com.example.domain.useCase.logout.ILogoutUserCase
import com.example.domain.useCase.logout.LogoutUserCase
import org.koin.dsl.module

val domainModule = module {
    factory <ILoginUserCase>{ LoginUserCase(get())}
    factory <ILogoutUserCase>{ LogoutUserCase(get())}
    factory <IGetMoviesCase>{ GetMoviesCase(get())}
    factory <IGetMoviesRoomCase>{ GetMoviesRoomCase(get())}
}

