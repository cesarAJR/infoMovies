package com.example.prueba_softtek.data.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies : List<MovieEntity>)

    @Query("SELECT * FROM table_movie order by title desc")
    fun getMovies() : Flow<List<MovieEntity>>

//    @Query("SELECT * FROM table_movie order by title desc")
//    fun getMoviesPerPage() : PagingSource<Int, MovieEntity>
}