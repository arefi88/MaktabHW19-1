package com.example.maktabhw19_1.repository

import com.example.maktabhw19_1.datasource.IRemoteDataSource
import com.example.maktabhw19_1.datasource.ILocalDataSource
import com.example.maktabhw19_1.local.entity.MovieEntity
import com.example.maktabhw19_1.utils.toResultFlow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val localDataSource:ILocalDataSource,private val remoteDataSource:IRemoteDataSource) {
      fun getAllPopularMovies(apiKey:String,page:Int)= toResultFlow {
          remoteDataSource.getAllPopularMovies(apiKey, page)
     }
//   suspend fun getAllPopularMovies(apiKey: String,page: Int)=remoteDataSource.getAllPopularMovies(apiKey,page)
//
      fun getAllComingMovies(apiKey: String,page: Int)= toResultFlow {
         remoteDataSource.getAllComingMovies(apiKey, page)
     }

     suspend fun insertMovieEntity(movieEntity: MovieEntity)=localDataSource.insertMovie(movieEntity)
     fun getAllMoviesEntity()=localDataSource.getAllMovies()

    suspend fun deleteAllMovies()=localDataSource.deleteAllMovies()
}