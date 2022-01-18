package com.example.mymovieapplication.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymovieapplication.databinding.FragmentDetailsBinding
import com.example.mymovieapplication.databinding.MovieLayoutAdaptorBinding
import com.example.mymovieapplication.fragments.HomeFragmentDirections
import com.example.mymovieapplication.models.Movieitem
import com.example.mymovieapplication.viewmodel.MovieViewModel

class MovieAdaptor :RecyclerView.Adapter<MovieAdaptor.MovieViewHolder>(){

    inner class MovieViewHolder(val binding: MovieLayoutAdaptorBinding)
        :RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Movieitem>(){
        override fun areItemsTheSame(oldItem: Movieitem, newItem: Movieitem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movieitem, newItem: Movieitem): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var movie:List<Movieitem>
    get() = differ.currentList
    set(value){
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieLayoutAdaptorBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currMovie = movie[position]

        holder.binding.apply {
            textView.text = currMovie.title
            imageView.load("https://image.tmdb.org/t/p/w342/"+currMovie.poster_path){
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener{mView->
            val direction = HomeFragmentDirections
                .actionHomeFragmentToDetailsFragment(currMovie)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount()= movie.size
}