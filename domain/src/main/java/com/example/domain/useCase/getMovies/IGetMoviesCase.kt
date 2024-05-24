package com.example.domain.useCase.getMovies

import androidx.paging.PagingData
import com.example.domain.model.Movie
import com.example.domain.core.Result
import kotlinx.coroutines.flow.Flow

interface IGetMoviesCase {

    suspend fun execute():Flow<PagingData<Movie>>

}