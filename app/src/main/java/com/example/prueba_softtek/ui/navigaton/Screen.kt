package com.example.prueba_softtek.ui.navigaton

sealed class Screen(val route : String) {

    object Login: Screen("login_screen")
    object List: Screen("list_screen")
    object ListRoom: Screen("list_room_screen")
    object Detail : Screen("detail_screen/?movie={movie}"){
        fun createRoute(movie:String) = "detail_screen/?movie=$movie"
    }
}