package com.example.data.datasource.local.movie

import com.example.data.database.model.toListDomainMovie
import com.example.data.database.model.toListLocalMovie
import com.example.domain.model.Movie
import com.example.prueba_softtek.data.database.dao.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class MovieLocalDataSource(private val dao: MovieDao) : IMovieLocalDataSource {
    override  fun save(movies: List<Movie>) {
        val moviesDb = movies.toListLocalMovie()
        dao.insert(moviesDb)
    }

    override suspend fun getMovies(): Flow<MutableList<Movie>>  = flow {
        dao.getMovies()?.map {
            it.toListDomainMovie()?.toMutableList()
        }?.collect{
            emit(it!!)
        }
    }
}