package com.example.mymovieapplication.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface movieDao {
    @Query("select * from movieTopRated")
    fun getAll():List<movieTopRated>
}