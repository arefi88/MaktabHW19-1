package com.example.maktabhw19_1.datasource

import com.example.maktabhw19_1.model.commingmovies.ComingSoonMovies
import com.example.maktabhw19_1.model.popularmovies.PopularMovies
import retrofit2.Response

interface IRemoteDataSource {
    suspend fun getAllPopularMovies(apiKey:String, page:Int): Response<PopularMovies>
    suspend fun getAllComingMovies( apiKey: String, page: Int): Response<ComingSoonMovies>


}