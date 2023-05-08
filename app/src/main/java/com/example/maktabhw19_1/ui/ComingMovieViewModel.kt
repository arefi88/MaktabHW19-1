package com.example.maktabhw19_1.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabhw19_1.model.commingmovies.ComingSoonMovies
import com.example.maktabhw19_1.repository.MovieRepository
import com.example.maktabhw19_1.utils.API_KEY
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComingMovieViewModel @Inject constructor(@ApplicationContext private val context: Context, private val repository: MovieRepository) :ViewModel() {
    private val _comingMoviesLiveData = MutableLiveData<ComingSoonMovies>()
    val comingMoviesLiveData: LiveData<ComingSoonMovies> = _comingMoviesLiveData
    val data=repository.getAllComingMovies(API_KEY,1)
//    fun getAllComingMovies()=viewModelScope.launch {
//        repository.getAllComingMovies("a447989f2b34e1193b1194c6265c3d3f",1).let { response->
//            if(response.isSuccessful){
//                Toast.makeText(context,"shod", Toast.LENGTH_SHORT).show()
//                _comingMoviesLiveData.postValue(response.body())
//            }else{
//                Toast.makeText(context,"${response.code()}", Toast.LENGTH_SHORT).show()
//
//            }
//        }
//    }
}