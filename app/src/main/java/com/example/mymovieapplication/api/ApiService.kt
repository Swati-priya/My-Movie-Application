package com.example.mymovieapplication.api

import com.example.mymovieapplication.models.MoviesResponse
import com.example.mymovieapplication.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


// moviedb:  https://api.themoviedb.org/3/movie/popular?api_key=52034b5afa4cc6c61b2b19a57223af86
//moviedetail: https://api.themoviedb.org/3/movie/512195?api_key=52034b5afa4cc6c61b2b19a57223af86
//poster: https://image.tmdb.org/t/p/w342/lAXONuqg41NwUMuzMiFvicDET9Y.jpg


interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getMovie(): Response<MoviesResponse>

}