package com.example.prueba_softtek.data.model

import com.example.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieUpComingResponse(
    @SerializedName("results")
    val list : List<MovieResponse>,
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("status_message")
    val statusMessage : String,
    @SerializedName("page")
    val page : Int
)
data class MovieResponse(
    @SerializedName("backdrop_path")
    val backdropPath:String,
    @SerializedName("id")
    val id:Int,
    @SerializedName("original_language")
    val originalLanguage:String,
    @SerializedName("original_title")
    val originalTitle:String,
    @SerializedName("overview")
    val overview:String,
    @SerializedName("popularity")
    val popularity:Double,
    @SerializedName("poster_path")
    val posterPath:String?=null,
    @SerializedName("release_date")
    val releaseDate:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("vote_average")
    val voteAverage:Double,
    @SerializedName("vote_count")
    val voteCount:Int,
)

fun List<MovieResponse>.toListMovie():List<Movie> = map{
    Movie(
        id = it.id,
        title = it.title,
        overview = it.overview,
        originalTitle = it.originalTitle,
        releaseDate = it.releaseDate,
        posterPath = it.posterPath,
        backdropPath = it.backdropPath,
        originalLanguage = it.originalLanguage,
        popularity = it.popularity,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount,
    )
}