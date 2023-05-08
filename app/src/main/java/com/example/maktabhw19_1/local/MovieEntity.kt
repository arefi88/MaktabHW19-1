package com.example.maktabhw19_1.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_tbl")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var title:String="",
    var releaseDate:String="",
    var posterPath:String=""
)
