package com.example.data.datasource

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.datasource.remote.movie.IMovieRemoteDataSource
import com.example.domain.model.Movie
import com.example.prueba_softtek.data.model.toListMovie
import kotlinx.coroutines.delay
import java.io.IOException

class MoviePagingSource(private val remoteDataSource: IMovieRemoteDataSource): PagingSource<Int, Movie>() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
       return try {
           val currentPage = params.key ?: 1
           val movies = remoteDataSource.getMovies(currentPage)
           delay(7000)
           LoadResult.Page(
               data = movies.list.toListMovie(),
               prevKey = if (currentPage==1)null else currentPage-1,
               nextKey = if (movies.list.isEmpty()) null else movies.page +1
           )
        }catch (e:IOException){
           return LoadResult.Error(e)
        }catch (e:HttpException){
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

}