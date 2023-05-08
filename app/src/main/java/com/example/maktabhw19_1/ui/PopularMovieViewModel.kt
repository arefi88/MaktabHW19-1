package com.example.maktabhw19_1.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabhw19_1.common.ApiState
import com.example.maktabhw19_1.model.commingmovies.ComingSoonMovies
import com.example.maktabhw19_1.model.popularmovies.PopularMovies
import com.example.maktabhw19_1.repository.MovieRepository
import com.example.maktabhw19_1.utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(@ApplicationContext private val context: Context,private val repository: MovieRepository) : ViewModel() {
    private val _popularMoviesLiveData = MutableLiveData<PopularMovies>()
    val popularMoviesLiveData: LiveData<PopularMovies> = _popularMoviesLiveData

//    private val _popularMoviesFlow = MutableStateFlow(ApiState.Loading)
//    private val popularMoviesFlow:StateFlow<ApiState<>> = _popularMoviesFlow

      val data=repository.getAllPopularMovies("a447989f2b34e1193b1194c6265c3d3f",1)

//    fun getAllPopularMovies()=viewModelScope.launch {
//        repository.getAllPopularMovies("a447989f2b34e1193b1194c6265c3d3f",1).let { response->
//            if(response.isSuccessful){
//
//                _popularMoviesLiveData.postValue(response.body())
//            }else{
//                Toast.makeText(context,"${response.code()}",Toast.LENGTH_SHORT).show()
//
//            }
//        }
//    }





}



