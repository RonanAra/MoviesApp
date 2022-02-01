package com.example.moviesapp.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.presentation.interfaces.MovieOnClickListener
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.presentation.home.adapter.PopularAdapter
import com.example.moviesapp.presentation.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModel()

    private val popularAdapter = PopularAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        setupRecyclerView()
        buttonSearch()
        buttonBookmark()


    }

    private fun buttonBookmark() {
        binding?.ibFavorite?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bookFragment)
        }
    }

    private fun buttonSearch() {
        binding?.ibSearch?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }


    private fun setupRecyclerView() {
        binding?.rvHomePopular?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = popularAdapter

        }
    }


    private fun setupObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPopular().collect { pagingData ->
                popularAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)

                popularAdapter.setMovieOnClickListener(object : MovieOnClickListener {
                    override fun onItemClick(movie: Movie) {
                        val direction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie)
                        findNavController().navigate(direction)
                    }
                })
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}
