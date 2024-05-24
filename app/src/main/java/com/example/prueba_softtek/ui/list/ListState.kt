package com.example.prueba_softtek.ui.list

import com.example.domain.model.Movie

data class ListState (
    val isRefreshing:Boolean=false,
    val isLoading:Boolean?=null,
    val error:String?=null,
    val movies:List<Movie>?=null
    )