package com.example.maktabhw19_1.model.commingmovies


import com.google.gson.annotations.SerializedName

data class ComingSoonMovies(
    @SerializedName("dates")
    val dates: Dates?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<ComingSoonResult?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)