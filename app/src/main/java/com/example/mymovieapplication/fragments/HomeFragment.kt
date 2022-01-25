package com.example.mymovieapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovieapplication.R
import com.example.mymovieapplication.adaptor.MovieAdaptor
import com.example.mymovieapplication.databinding.FragmentHomeBinding
import com.example.mymovieapplication.room.FavoriteMovie
import com.example.mymovieapplication.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdaptor: MovieAdaptor
    private lateinit var favMovie: List<FavoriteMovie>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val favbtn = _binding!!.favButton
        favbtn.setOnClickListener {
            val direction = HomeFragmentDirections
                .actionHomeFragmentToFavoriteFragment()
            it.findNavController().navigate(direction)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        movieAdaptor = MovieAdaptor()
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            setHasFixedSize(true)
            adapter = movieAdaptor
        }

        viewModel.moviesResponse.observe(
            requireActivity(),
            { response ->
                movieAdaptor.movie = response.results
            }
        )
    }
}