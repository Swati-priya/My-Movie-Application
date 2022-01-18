package com.example.mymovieapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapplication.models.MoviesResponse
import com.example.mymovieapplication.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject constructor(private val repository: MovieRepository)
    :ViewModel(){

    private val _response = MutableLiveData<MoviesResponse>()
    val moviesResponse:LiveData<MoviesResponse>
    get()= _response

    init {
        getMovie()
    }

    private fun getMovie() = viewModelScope.launch {

        repository.getMovie().let { response ->
            if(response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("tag","getMovie Error: ${response.code()}")
            }
        }
    }
}
