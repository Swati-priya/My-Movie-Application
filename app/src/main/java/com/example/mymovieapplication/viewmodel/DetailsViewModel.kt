package com.example.mymovieapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mymovieapplication.models.Movieitem
import com.example.mymovieapplication.repository.FavoriteMovieRepository
import com.example.mymovieapplication.room.FavoriteMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: FavoriteMovieRepository
) : ViewModel() {

    fun addToFavorite(movieitem: Movieitem) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFavorite(
                FavoriteMovie(
                    movieitem.id.toString(),
                    movieitem.original_title,
                    movieitem.overview,
                    movieitem.poster_path
                )
            )
        }
    }

    suspend fun checkMovie(id: String) = repository.checkMovie(id)
    fun removeFromFavorite(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeFromFavorite(id)
        }
    }
}
