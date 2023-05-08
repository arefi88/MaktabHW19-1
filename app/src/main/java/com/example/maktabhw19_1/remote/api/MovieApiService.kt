package com.example.maktabhw19_1.remote.api

import com.example.maktabhw19_1.model.commingmovies.ComingSoonMovies
import com.example.maktabhw19_1.model.commingmovies.ComingSoonResult
import com.example.maktabhw19_1.model.popularmovies.PopularMovies
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getAllPopularMovies(@Query("api_key") apiKey:String,@Query("page") page:Int):Response<PopularMovies>

     @GET("movie/upcoming")
    suspend fun getAllComingMovies(@Query("api_key") apiKey: String , @Query("page") page: Int):Response<ComingSoonMovies>

}