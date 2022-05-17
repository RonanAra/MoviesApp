package com.example.moviesapp.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.presentation.home.adapter.PopularAdapter
import com.example.moviesapp.presentation.home.adapter.RatedAdapter
import com.example.moviesapp.presentation.home.adapter.RecommendAdapter
import com.example.moviesapp.presentation.home.viewmodel.HomeViewModel
import com.example.moviesapp.presentation.interfaces.MovieOnClickListener
import com.example.moviesapp.utils.Command
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModel()
    var command: MutableLiveData<Command> = MutableLiveData()
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


        viewModel.command = command
        viewModel.getRecommend()
        viewModel.getTopRated()

        setupObservablesTop()
        setupObservablesPopular()
        setupRecyclerView()
        initButtons()
        setupObservablesRecommend()
        clickPopularAdapter()

    }

    private fun setupObservablesTop() {
        viewModel.onSuccessTopRated.observe(viewLifecycleOwner) {
            it?.let {
                val adapterTopRated = RatedAdapter(listaMovies = it)

                adapterTopRated.setMovieOnClickListener(object : MovieOnClickListener {
                    override fun onItemClick(movie: Movie) {
                        val direction =
                            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie)
                        findNavController().navigate(direction)
                    }
                })
                binding?.let {
                    with(it) {
                        rvHomeRated.apply {
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            adapter = adapterTopRated
                        }
                    }
                }
            }
        }
    }

    private fun setupObservablesRecommend() {
        viewModel.onSuccessRecommend.observe(viewLifecycleOwner) { listMovies ->
            val recommendAdapter = RecommendAdapter(
                listaMovies = listMovies
            )

            recommendAdapter.setMovieOnClickListener(object : MovieOnClickListener {
                override fun onItemClick(movie: Movie) {
                    val direction =
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie)
                    findNavController().navigate(direction)
                }
            })

            binding?.let {
                with(it) {
                    rvHomeRecommend.apply {
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = recommendAdapter
                    }
                }
            }
        }
    }


    private fun setupObservablesPopular() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPopular().collect { pagingData ->
                popularAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)

            }
        }
    }


    private fun clickPopularAdapter() {
        popularAdapter.setMovieOnClickListener(object : MovieOnClickListener {
            override fun onItemClick(movie: Movie) {
                val direction =
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie)
                findNavController().navigate(direction)
            }
        })
    }

    private fun setupRecyclerView() {
        binding?.rvHomePopular?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
    }

    private fun initButtons() {
        binding?.ibFavorite?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bookFragment)
        }
        binding?.ibSearch?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

