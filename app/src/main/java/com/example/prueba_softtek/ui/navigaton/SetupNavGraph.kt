package com.example.prueba_softtek.ui.navigaton

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.domain.model.Movie
import com.example.prueba_softtek.ui.detail.DetailScreen
import com.example.prueba_softtek.ui.list.ListScreen
import com.example.prueba_softtek.ui.listRoom.ListRoomScreen
import com.example.prueba_softtek.ui.login.LoginScreen
import com.google.gson.Gson

@Composable
fun SetupNavGraph(navController: NavHostController,isLogged : Boolean)  {

    val startDestination =  if(isLogged){
        Screen.List.route}else{
        Screen.Login.route}

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(){
                navController.popBackStack()
                navController.navigate(Screen.List.route)
            }
        }
        composable(
            route = Screen.List.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(300)
                )
            }
        ) {
            ListScreen(
            onDetail = {
                    val movieJson =  Gson().toJson(it)
                    navController.navigate(Screen.Detail.createRoute(movieJson))
            },
            onLogin = {
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            },
            onListRoom = {
                navController.navigate(Screen.ListRoom.route)
            }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("movie"){defaultValue = "" },
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(300)
                )
            }
        ) {backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movie")
            val movie =  Gson().fromJson(movieJson, Movie::class.java)
            requireNotNull(movie)
            DetailScreen(movie = movie){
                navController.navigateUp()
            }
        }

        composable(
            route = Screen.ListRoom.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(300)
                )
            }
        ) {
            ListRoomScreen {
                val movieJson =  Gson().toJson(it)
                navController.navigate(Screen.Detail.createRoute(movieJson))
            }
        }

    }
}