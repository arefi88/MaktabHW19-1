package com.example.maktabhw19_1.datasource

import com.example.maktabhw19_1.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun insertMovie(movieEntity: MovieEntity)
    fun getAllMovies(): Flow<List<MovieEntity>>
    suspend fun deleteAllMovies()
}