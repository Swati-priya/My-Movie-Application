package com.example.mymovieapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.mymovieapplication.R
import com.example.mymovieapplication.databinding.FragmentDetailsBinding
import com.example.mymovieapplication.models.Movieitem

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding : FragmentDetailsBinding?= null
    private val binding get() = _binding!!
    private val  args:DetailsFragmentArgs by navArgs()
    private lateinit var movie : Movieitem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(
                inflater,container,false
                )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = args.movie
        populateUI()
    }

    private fun populateUI(){

        binding.apply {

            tvDetails.text = movie.overview
            imageViewDetail.load("https://image.tmdb.org/t/p/w342/"+movie.poster_path){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}