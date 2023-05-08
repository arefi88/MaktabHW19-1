package com.example.maktabhw19_1.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie_tbl")
    fun getAllMovies():Flow<List<MovieEntity>>

    @Query("DELETE FROM movie_tbl")
    suspend fun deleteAllMovies()
}