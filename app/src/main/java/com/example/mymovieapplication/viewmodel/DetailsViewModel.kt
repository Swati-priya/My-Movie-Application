package com.example.mymovieapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapplication.models.Movieitem
import com.example.mymovieapplication.repository.FavoriteMovieRepository
import com.example.mymovieapplication.room.FavoriteMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: FavoriteMovieRepository
) : ViewModel() {

    private val _isMovieExist = MutableLiveData<Boolean>()
    // val isMovieExist: LiveData<Int> = _isMovieExist

    fun addToFavorite(movieItem: Movieitem) = viewModelScope.launch(Dispatchers.IO) {
        repository.addToFavorite(
            FavoriteMovie(
                movieItem.id.toString(),
                movieItem.original_title,
                movieItem.overview,
                movieItem.poster_path
            )
        )
    }

    suspend fun checkMovie(id: String) = repository.checkMovie(id)
    fun checkMovie1(id: String) = viewModelScope.launch(Dispatchers.IO) {

        val movieCount = repository.checkMovie(id)
        println("favorite movie:  $movieCount")
        if (movieCount < 0) {
            _isMovieExist.postValue(false)
        } else {
            _isMovieExist.postValue(true)
        }
    }

    // fun isMovieExist() = _isMovieExist.value

    fun removeFromFavorite(id: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.removeFromFavorite(id)
    }
}
