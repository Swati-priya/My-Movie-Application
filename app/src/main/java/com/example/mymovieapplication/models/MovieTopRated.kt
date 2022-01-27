package com.example.mymovieapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class MovieTopRated(
    @ColumnInfo(name = "Page")
    val page: Int,
    @ColumnInfo(name = "topMovieItem")
    val results: List<TopMovieItem>,
    @ColumnInfo(name = "totalPage")
    val total_pages: Int,
    @ColumnInfo(name = "top_result")
    val total_results: Int
)
