package com.example.maktabhw19_1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabhw19_1.local.MovieEntity
import com.example.maktabhw19_1.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectedMoviesViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private val _selectedMovieLiveData = MutableLiveData<List<MovieEntity>>()
    val selectedMovieLiveData: LiveData<List<MovieEntity>> = _selectedMovieLiveData

    init {
        getSelectedMovies()
    }

    private fun getSelectedMovies()=viewModelScope.launch {
        repository.getAllMoviesEntity().collect{
            _selectedMovieLiveData.value=it
        }
    }


}