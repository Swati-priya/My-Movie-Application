package com.example.mymovieapplication.repository

import com.example.mymovieapplication.api.ApiService
import javax.inject.Inject

class MovieRepository
@Inject constructor(private val apiService: ApiService)
{
    suspend fun getMovie()= apiService.getMovie()
}