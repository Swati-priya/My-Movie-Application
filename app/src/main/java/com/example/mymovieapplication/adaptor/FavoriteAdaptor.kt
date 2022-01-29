package com.example.mymovieapplication.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymovieapplication.databinding.ItemMovieBinding
import com.example.mymovieapplication.room.FavoriteMovie

class FavoriteAdaptor : RecyclerView.Adapter<FavoriteAdaptor.FavoriteViewHolder>() {
    inner class FavoriteViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<FavoriteMovie>() {
        override fun areItemsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
            return oldItem.id_movie == newItem.id_movie
        }

        override fun areContentsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movie: List<FavoriteMovie>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currMovie = movie[position]
        holder.binding.apply {
            movieTitle.text = currMovie.original_title
            moviePoster.load("https://image.tmdb.org/t/p/w342/" + currMovie.poster_path) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount() = movie.size
}
