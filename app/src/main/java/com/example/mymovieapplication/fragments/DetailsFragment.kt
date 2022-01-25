package com.example.mymovieapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.mymovieapplication.R
import com.example.mymovieapplication.databinding.FragmentDetailsBinding
import com.example.mymovieapplication.models.Movieitem
import com.example.mymovieapplication.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var movie: Movieitem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = args.movie
        populateUI()
    }

    private fun populateUI() {
        binding.apply {
            movieTitle.text = movie.title
            movieRating.text = movie.vote_average.toString()
            movieReleaseDate.text = movie.release_date
            moviePopularity.text = movie.popularity.toString()
            tvDetails.text = movie.overview

            var _isChecked = false
            CoroutineScope(Dispatchers.IO).launch {
                val count = viewModel.checkMovie(movie.id.toString())
                withContext(Main) {
                    if (count > 0) {
                        toggleFavorite.isChecked = true
                        _isChecked = true
                    } else {
                        toggleFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }

            toggleFavorite.setOnClickListener {
                _isChecked = !_isChecked
                if (_isChecked) {
                    viewModel.addToFavorite(movie)
                } else {
                    viewModel.removeFromFavorite(movie.id.toString())
                }
                toggleFavorite.isChecked = _isChecked
            }

            imageViewDetail.load("https://image.tmdb.org/t/p/w342/" + movie.poster_path) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }
}