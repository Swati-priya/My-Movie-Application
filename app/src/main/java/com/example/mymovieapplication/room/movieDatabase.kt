package com.example.mymovieapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [movieTopRated::class, Result::class], version = 1)
abstract class movieDatabase: RoomDatabase() {

    abstract fun getmovieDoa(): movieDao
    companion object{
        @Volatile
        var INSTANCE: movieDatabase?= null

         fun getDatabase(context: Context):movieDatabase{
             return INSTANCE ?: synchronized(this){
                 val instance = Room.databaseBuilder(context.applicationContext, movieDatabase::class.java, "movie_db").build()
                 INSTANCE= instance
                 instance
             }
         }
    }
}

