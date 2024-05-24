package com.example.data.datasource.remote.movie

import com.example.prueba_softtek.data.model.MovieUpComingResponse

interface IMovieRemoteDataSource {
    suspend fun getMovies(page:Int): MovieUpComingResponse
}