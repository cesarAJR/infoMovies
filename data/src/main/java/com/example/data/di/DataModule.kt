package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.datasource.local.movie.IMovieLocalDataSource
import com.example.data.datasource.local.movie.MovieLocalDataSource
import com.example.data.datasource.remote.login.ILoginRemoteDataSource
import com.example.data.datasource.remote.login.LoginRemoteDataSource
import com.example.data.datasource.remote.movie.IMovieRemoteDataSource
import com.example.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.domain.repository.IMovieRepository
import com.example.domain.repository.IUserRepository
import com.example.prueba_softtek.core.BasicInterceptor
import com.example.prueba_softtek.data.database.AppDatabase
import com.example.prueba_softtek.data.database.dao.MovieDao
import com.example.prueba_softtek.data.remote.Methods
import com.example.prueba_softtek.data.repository.MovieRepository
import com.example.prueba_softtek.data.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val dataModule = module {
    single { provideBasicInterceptor() }
    single { provideRetrofit(get()) }
    single { provideDatabase(androidContext()) }
    single { provideMovieDao(get())  }
    single { provideFirebase()}
    factory<IMovieLocalDataSource> { MovieLocalDataSource(get()) }
    factory<IMovieRemoteDataSource> { MovieRemoteDataSource(get()) }
    factory<ILoginRemoteDataSource> { LoginRemoteDataSource(get()) }
    factory<IMovieRepository> { MovieRepository(get(),get(),get()) }
    factory<IUserRepository> { UserRepository(get()) }
}

fun provideFirebase():FirebaseAuth{
    return Firebase.auth
}

fun provideRetrofit(okHttpClient: OkHttpClient) : Methods {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create()
}

fun provideBasicInterceptor() : OkHttpClient{
    return OkHttpClient.Builder()
        .addInterceptor(BasicInterceptor())
        .build()
}

fun provideDatabase(context : Context) : AppDatabase = Room.databaseBuilder(
    context,
    AppDatabase::class.java,
    "dbMovie"
).build()

fun provideMovieDao(appDatabase: AppDatabase) : MovieDao = appDatabase.MovieDao()