package com.example.maktabhw19_1.model.popularmovies


import com.google.gson.annotations.SerializedName

data class PopularMovies(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<ResultPopular?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)