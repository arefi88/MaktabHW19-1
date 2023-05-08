package com.example.maktabhw19_1.remote

import com.example.maktabhw19_1.datasource.DataSource
import com.example.maktabhw19_1.local.MovieEntity
import com.example.maktabhw19_1.model.commingmovies.ComingSoonMovies
import com.example.maktabhw19_1.model.popularmovies.PopularMovies
import com.example.maktabhw19_1.remote.api.MovieApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val apiService: MovieApiService) :DataSource {
    override suspend fun getAllPopularMovies(apiKey: String, page: Int): Response<PopularMovies> =
        apiService.getAllPopularMovies(apiKey, page)

    override suspend fun getAllComingMovies(apiKey: String, page: Int): Response<ComingSoonMovies> =
        apiService.getAllComingMovies(apiKey,page)

}


