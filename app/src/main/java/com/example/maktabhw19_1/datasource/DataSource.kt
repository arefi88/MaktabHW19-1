package com.example.maktabhw19_1.datasource

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.maktabhw19_1.local.MovieEntity
import com.example.maktabhw19_1.model.commingmovies.ComingSoonMovies
import com.example.maktabhw19_1.model.popularmovies.PopularMovies
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataSource {
    suspend fun getAllPopularMovies(apiKey:String, page:Int): Response<PopularMovies>
    suspend fun getAllComingMovies( apiKey: String, page: Int): Response<ComingSoonMovies>


}