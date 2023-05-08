package com.example.maktabhw19_1.model.moviedetails


import com.example.maktabhw19_1.model.moviedetails.Result
import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("results")
    val results: List<Result?>?
)