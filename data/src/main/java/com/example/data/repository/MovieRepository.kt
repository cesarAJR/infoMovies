package com.example.prueba_softtek.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.datasource.MoviePagingSource
import com.example.data.datasource.local.movie.IMovieLocalDataSource
import com.example.data.datasource.remote.movie.IMovieRemoteDataSource
import com.example.domain.core.Result
import com.example.domain.model.Movie
import com.example.domain.repository.IMovieRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(private val movieRemoteDataSource: IMovieRemoteDataSource,
                      private val movieLocalDataSource: IMovieLocalDataSource,
                      private val auth: FirebaseAuth
): IMovieRepository {

    override suspend fun getMovies(): Flow<PagingData<Movie>> {

        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(movieRemoteDataSource)
            }
        ).flow



//      try {
//
//          val response  = movieRemoteDataSource.getMovies()
//          if(!response.success){
//              val data = response.list.toListMovie()
//              emit(Result.Successfull(data=data))
//              movieLocalDataSource.save(data)
//          }else{
//              emit(Result.Error(message = response.statusMessage))
//          }
//      }catch (ex:HttpException){
//          emit(Result.Error(message = "Se encontro un error"))
//      }catch (ex: IOException){
//          movieLocalDataSource.getMovies().collect{
//              emit(Result.Successfull(data =it))
//          }
//      } catch (ex: Exception) {
//          emit(Result.Error(message = ex.message.toString()))
//      }
    }

    override suspend fun getMoviesRoom(): Flow<Result<List<Movie>>> = flow {
        movieLocalDataSource.getMovies().collect{
            emit(Result.Successfull(data =it))
        }
    }

    override suspend fun save(movies: List<Movie>) {

    }

    override suspend fun resetLogin() {
        auth.signOut()
    }
}