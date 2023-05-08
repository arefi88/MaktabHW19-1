package com.example.maktabhw19_1.local

import com.example.maktabhw19_1.datasource.DataSource
import com.example.maktabhw19_1.datasource.ILocalDataSource
import com.example.maktabhw19_1.model.commingmovies.ComingSoonMovies
import com.example.maktabhw19_1.model.popularmovies.PopularMovies
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class LocalDataSource(private val movieDao: MovieDao) : ILocalDataSource{

    override suspend fun insertMovie(movieEntity: MovieEntity) {
       movieDao.insertMovie(movieEntity)
    }

    override fun getAllMovies(): Flow<List<MovieEntity>> {
       return movieDao.getAllMovies()
    }
}