package com.example.prueba_softtek.ui.listRoom

import com.example.domain.model.Movie

data class ListRoomState (
    val isRefreshing:Boolean=false,
    val isLoading:Boolean?=null,
    val error:String?=null,
    val movies:List<Movie>?=null
    )