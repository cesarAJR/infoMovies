package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import com.example.domain.core.Result
interface IMovieRepository {
    suspend fun getMovies(): Flow<PagingData<Movie>>

    suspend fun getMoviesRoom(): Flow<Result<List<Movie>>>
    suspend fun save(movies:List<Movie>)
    suspend fun resetLogin()
}