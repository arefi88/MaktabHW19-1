package com.example.maktabhw19_1.local

import com.example.maktabhw19_1.datasource.ILocalDataSource
import com.example.maktabhw19_1.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) : ILocalDataSource{

    override suspend fun insertMovie(movieEntity: MovieEntity) {
       movieDao.insertMovie(movieEntity)
    }

    override fun getAllMovies(): Flow<List<MovieEntity>> {
       return movieDao.getAllMovies()
    }

    override suspend fun deleteAllMovies() {
        movieDao.deleteAllMovies()
    }
}