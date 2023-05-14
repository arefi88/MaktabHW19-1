package com.example.maktabhw19_1.ui.popular

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabhw19_1.common.ApiState
import com.example.maktabhw19_1.model.commingmovies.ComingSoonMovies
import com.example.maktabhw19_1.model.popularmovies.PopularMovies
import com.example.maktabhw19_1.model.popularmovies.ResultPopular
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
    private val _popularMoviesLiveData = MutableLiveData<PopularMovies?>()
    val popularMoviesLiveData: LiveData<PopularMovies?> = _popularMoviesLiveData

    var page=1
    var isLoading=false

      fun getAllPopularMovies(view: View, apiKey:String, page:Int)=viewModelScope.launch {
          repository.getAllPopularMovies(apiKey,page).collect{
              when(it){
                  is ApiState.Success-> {
                      isLoading = false
                      view.isVisible = false
                      _popularMoviesLiveData.postValue(it.data)
                  }
                  is ApiState.Error->{
                     view.isVisible=false
                      Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
                  }
                  ApiState.Loading->{

                      view.isVisible=true

                  }
              }

          }
      }



}



