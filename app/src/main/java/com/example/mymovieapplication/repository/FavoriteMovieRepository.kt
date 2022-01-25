package com.example.mymovieapplication.repository

import com.example.mymovieapplication.room.FavoriteMovie
import com.example.mymovieapplication.room.FavoriteMovieDao
import javax.inject.Inject

class FavoriteMovieRepository
@Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) {
    suspend fun addToFavorite(favoriteMovie: FavoriteMovie) =
        favoriteMovieDao.addToFavorite(favoriteMovie)

    fun getFavoriteMovies() = favoriteMovieDao.getFavoriteMovie()

    suspend fun checkMovie(id: String) = favoriteMovieDao.checkMovie(id)

    suspend fun removeFromFavorite(id: String) {
        favoriteMovieDao.removeFromFavorite(id)
    }
}
