package com.example.data.datasource.local.movie

import com.example.data.database.model.MovieEntity
import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieLocalDataSource {
     fun save(movies: List<Movie>)
    suspend fun getMovies() : Flow<MutableList<Movie>>
}