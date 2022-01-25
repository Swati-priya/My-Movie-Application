package com.example.mymovieapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovieapplication.R
import com.example.mymovieapplication.adaptor.FavoriteAdaptor
import com.example.mymovieapplication.databinding.FragmentFavoriteBinding
import com.example.mymovieapplication.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private val viewModel by viewModels<FavoriteViewModel>()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteAdaptor: FavoriteAdaptor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFavRecyclerView()
    }

    private fun setUpFavRecyclerView() {
        favoriteAdaptor = FavoriteAdaptor()
        binding.recyclerView1.apply {
            layoutManager = GridLayoutManager(activity, 2)
            setHasFixedSize(true)
            adapter = favoriteAdaptor
        }

        viewModel.movies.observe(
            requireActivity(),
            { response ->
                favoriteAdaptor.movie = response
            }
        )
    }
}
