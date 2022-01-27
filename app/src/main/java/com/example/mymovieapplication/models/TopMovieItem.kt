package com.example.mymovieapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopMovieItem(
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdropPath")
    val backdrop_path: String,
    @ColumnInfo(name = "genreId")
    val genre_ids: List<Int>,
    @ColumnInfo(name = "id")
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "original_language")
    val original_language: String,
    @ColumnInfo(name = "original_title")
    val original_title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "posterPath")
    val poster_path: String,
    @ColumnInfo(name = "releaseDate")
    val release_date: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "voteAverage")
    val vote_average: Double,
    @ColumnInfo(name = "voteCount")
    val vote_count: Int
)
