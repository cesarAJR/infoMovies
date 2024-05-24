package com.example.domain.useCase.getMoviesRoom

import com.example.domain.model.Movie
import com.example.domain.repository.IMovieRepository
import com.example.domain.core.Result
import kotlinx.coroutines.flow.Flow

class GetMoviesRoomCase(val repository: IMovieRepository) : IGetMoviesRoomCase {

    override suspend fun execute(): Flow<Result<List<Movie>>> {
        return repository.getMoviesRoom()
    }
}