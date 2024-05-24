package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Movie

@Entity(tableName = "table_movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id:String,
    @ColumnInfo("backdrop_path")
    val backdropPath:String?=null,
    @ColumnInfo("original_language")
    val originalLanguage:String,
    @ColumnInfo("original_title")
    val originalTitle:String,
    @ColumnInfo("overview")
    val overview:String,
    @ColumnInfo("popularity")
    val popularity:Double,
    @ColumnInfo("poster_path")
    val posterPath:String?=null,
    @ColumnInfo("release_date")
    val releaseDate:String,
    @ColumnInfo("title")
    val title:String,
    @ColumnInfo("vote_average")
    val voteAverage:Double,
    @ColumnInfo("vote_count")
    val voteCount:Int
)

fun List<Movie>.toListLocalMovie() : List<MovieEntity> = map {
    MovieEntity(
        id = it.id.toString(),
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

fun List<MovieEntity>.toListDomainMovie() : List<Movie> = map {
    Movie(
        id = it.id.toInt(),
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

