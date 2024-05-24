package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id:Int,
    val title:String,
    val overview:String,
    val originalTitle:String,
    val releaseDate:String,
    val posterPath:String?=null,
    val backdropPath:String?=null,
    val originalLanguage:String,
    val popularity:Double,
    val voteAverage:Double,
    val voteCount:Int
) : Parcelable

//fun List<MovieResponse>.toListMovie():List<Movie> = map{
//    Movie(
//        id = it.id,
//        title = it.title,
//        overview = it.overview,
//        originalTitle = it.originalTitle,
//        releaseDate = it.releaseDate,
//        posterPath = it.posterPath,
//        backdropPath = it.backdropPath,
//        originalLanguage = it.originalLanguage,
//        popularity = it.popularity,
//        voteAverage = it.voteAverage,
//        voteCount = it.voteCount,
//    )
//}
//
//fun List<Movie>.toListLocalMovie() : List<MovieDb> = map {
//    MovieDb(
//        id = it.id.toString(),
//        title = it.title,
//        overview = it.overview,
//        originalTitle = it.originalTitle,
//        releaseDate = it.releaseDate,
//        posterPath = it.posterPath,
//        backdropPath = it.backdropPath,
//        originalLanguage = it.originalLanguage,
//        popularity = it.popularity,
//        voteAverage = it.voteAverage,
//        voteCount = it.voteCount,
//    )
//}
//
//fun List<MovieDb>.toListDomainMovie() : List<Movie> = map {
//    Movie(
//        id = it.id.toInt(),
//        title = it.title,
//        overview = it.overview,
//        originalTitle = it.originalTitle,
//        releaseDate = it.releaseDate,
//        posterPath = it.posterPath,
//        backdropPath = it.backdropPath,
//        originalLanguage = it.originalLanguage,
//        popularity = it.popularity,
//        voteAverage = it.voteAverage,
//        voteCount = it.voteCount,
//    )
//}