package com.example.maktabhw19_1.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_tbl", primaryKeys = ["id","title","releaseDate","posterPath"])
data class MovieEntity(

    var id:Int=0,

    var title:String="",

    var releaseDate:String="",

    var posterPath:String=""
)
