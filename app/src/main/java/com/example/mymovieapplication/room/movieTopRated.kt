package com.example.mymovieapplication.room

import androidx.room.Entity

@Entity
data class movieTopRated(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)