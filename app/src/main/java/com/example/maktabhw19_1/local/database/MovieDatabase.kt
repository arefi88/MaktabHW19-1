package com.example.maktabhw19_1.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.maktabhw19_1.local.MovieDao
import com.example.maktabhw19_1.local.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getDao():MovieDao
}