package com.example.prueba_softtek.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prueba_softtek.data.database.dao.MovieDao
import com.example.data.database.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun MovieDao():MovieDao
}