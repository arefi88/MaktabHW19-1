package com.example.maktabhw19_1.remote

import com.example.maktabhw19_1.datasource.IRemoteDataSource
import com.example.maktabhw19_1.model.commingmovies.ComingSoonMovies
import com.example.maktabhw19_1.model.popularmovies.PopularMovies
import com.example.maktabhw19_1.remote.api.MovieApiService
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val apiService: MovieApiService) :IRemoteDataSource {
    override suspend fun getAllPopularMovies(apiKey: String, page: Int): Response<PopularMovies> =
        apiService.getAllPopularMovies(apiKey, page)

    override suspend fun getAllComingMovies(apiKey: String, page: Int): Response<ComingSoonMovies> =
        apiService.getAllComingMovies(apiKey,page)

}


