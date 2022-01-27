package com.example.mymovieapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mymovieapplication.repository.FavoriteMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteMovieRepository
) : ViewModel() {
    val movies = repository.getFavoriteMovies()
}
