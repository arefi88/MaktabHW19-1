package com.example.maktabhw19_1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabhw19_1.local.MovieEntity
import com.example.maktabhw19_1.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {
    fun insertMovieEntity(movieEntity: MovieEntity)=viewModelScope.launch {
        repository.insertMovieEntity(movieEntity)
    }
}