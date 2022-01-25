package com.example.mymovieapplication.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "favorite_movie")
@Parcelize
data class FavoriteMovie(
    val id_movie: String,
    val original_title: String,
    val overview: String?,
    val poster_path: String
) : Serializable, Parcelable {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    val baseUrl get() = "https://image.tmdb.org/t/p/w342"
}
