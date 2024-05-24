package com.example.domain.useCase.getMovies

import androidx.paging.PagingData
import com.example.domain.model.Movie
import com.example.domain.repository.IMovieRepository
import com.example.domain.core.Result
import kotlinx.coroutines.flow.Flow

class GetMoviesCase(val repository: IMovieRepository) : IGetMoviesCase {

    override suspend fun execute(): Flow<PagingData<Movie>> {
        return repository.getMovies()
    }
}