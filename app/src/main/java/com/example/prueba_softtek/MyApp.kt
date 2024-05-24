package com.example.prueba_softtek

import android.app.Application
import com.example.data.di.dataModule
import com.example.data.di.domainModule
import com.example.prueba_softtek.di.appModule
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import kotlin.system.measureTimeMillis


class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MyApp)
            modules(dataModules + domainModules + appModules)
        }
//        runBlocking {
//            println("Weather forecast")
//            println(getWeatherReport())
//            println("Have a good day!")
//        }

//        runBlocking {
//            println("${Thread.currentThread().name} - runBlocking function")
//            launch {
//                println("${Thread.currentThread().name} - launch function")
//                withContext(Dispatchers.Default) {
//                    println("${Thread.currentThread().name} - withContext function")
//                    delay(1000)
//                    println("10 results found.")
//                }
//                println("${Thread.currentThread().name} - end of launch function")
//            }
//            println("Loading...")
//        }
    }
}

suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async {
        try {
            getTemperature()
        } catch (e: AssertionError) {
            println("Caught exception $e")
            "{ No temperature found }"
        }
    }

    temperature.cancel()
    "${forecast.await()}"
}


suspend fun getForecast():String {
    delay(2000)
    return  "Sunny"
}

suspend fun getTemperature() : String{
    delay(7000)
    return "30\u00b0C"
}

val appModules = listOf(appModule)
val domainModules = listOf(domainModule)
val dataModules = listOf(dataModule)