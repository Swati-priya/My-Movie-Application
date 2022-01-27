package com.example.mymovieapplication.models

data class MoviesResponse(
    val page: Int,
    val results: List<Movieitem>,
    val total_pages: Int,
    val total_results: Int
)
