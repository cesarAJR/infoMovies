package com.example.prueba_softtek

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.example.potencie21compose.ui.theme.PruebaSofttekComposeTheme
import com.example.prueba_softtek.ui.navigaton.SetupNavGraph
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {


    private val firebaseAuth: FirebaseAuth by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaSofttekComposeTheme {
                val navController = rememberNavController()
                val isLogged =  firebaseAuth.currentUser!=null
                SetupNavGraph(navController = navController,isLogged =  isLogged)
            }
        }
    }
}