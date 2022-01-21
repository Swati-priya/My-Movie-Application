package com.example.mymovieapplication.room

import android.content.Context

class movieroomrepo {

    lateinit var movieDao: movieDao

    constructor(context: Context): this() {
        val moviedatabase = movieDatabase.getDatabase(context)
        movieDao= moviedatabase.getmovieDoa()
    }

    constructor()


    fun getallmovie(): List<movieTopRated> {
        return movieDao.getAll()
    }
}