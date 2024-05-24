package com.example.domain.useCase.getMoviesRoom

import com.example.domain.model.Movie
import com.example.domain.core.Result
import kotlinx.coroutines.flow.Flow

interface IGetMoviesRoomCase {

    suspend fun execute():Flow<Result<List<Movie>>>

}