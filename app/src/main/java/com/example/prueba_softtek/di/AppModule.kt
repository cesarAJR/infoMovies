package com.example.prueba_softtek.di

import android.content.Context
import android.content.SharedPreferences
import com.example.prueba_softtek.viewModel.ListRoomViewModel
import com.example.prueba_softtek.viewModel.ListViewModel
import com.example.prueba_softtek.viewModel.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { provideSharePreferences(androidContext())  }
    viewModel { ListViewModel(get(),get()) }
    viewModel { ListRoomViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}


fun provideSharePreferences(context : Context) : SharedPreferences {
    return context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
}
