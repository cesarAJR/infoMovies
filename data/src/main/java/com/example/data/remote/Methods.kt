package com.example.prueba_softtek.data.remote

import com.example.prueba_softtek.data.model.MovieUpComingResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Methods {
    @Headers(
        "Accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NjQ3ZDZkYjVjYjliYjkwYWE4ODc0NjU0Zjc1YzhmMiIsInN1YiI6IjVmMGJmMzcwMWY5OGQxMDAzNzI0MzcwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.G7xyxqM9AbeLWiBpjDPMuUvqHbvBpZb5F77WRbIm9OU",
    )
    @GET("movie/upcoming")
    suspend fun getMovies(@Query("page") page:Int): MovieUpComingResponse

}