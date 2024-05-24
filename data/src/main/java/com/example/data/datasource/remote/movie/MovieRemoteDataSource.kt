package com.example.data.datasource.remote.movie

import com.example.prueba_softtek.data.model.MovieUpComingResponse
import com.example.prueba_softtek.data.remote.Methods

class MovieRemoteDataSource(private val methods: Methods) : IMovieRemoteDataSource {
    override suspend fun getMovies(page:Int): MovieUpComingResponse {
           return methods.getMovies(page)
    }
}