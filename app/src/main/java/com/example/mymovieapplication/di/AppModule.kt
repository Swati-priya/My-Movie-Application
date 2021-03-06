package com.example.mymovieapplication.di

import android.content.Context
import androidx.room.Room
import com.example.mymovieapplication.api.ApiService
import com.example.mymovieapplication.room.FavoriteMovieDatabase
import com.example.mymovieapplication.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): ApiService =
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    @Provides
    @Singleton
    fun provideFavMovieDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FavoriteMovieDatabase::class.java,
        "movieDb"
    ).build()

    @Provides
    @Singleton
    fun provideFavMovieDao(db: FavoriteMovieDatabase) = db.getFavoriteMovieDao()
}
