package com.example.moviesapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.presentation.home.adapter.HomeMoviesAdapter
import com.example.moviesapp.utils.extensions.notImplFeature
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val popularAdapter by lazy { HomeMoviesAdapter {} }
    private val recommendedAdapter by lazy { HomeMoviesAdapter {} }
    private val topRatedAdapter by lazy { HomeMoviesAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        setListeners()
        collectUiState()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onEvent(HomeEvent.LoadMovies)
    }

    private fun setupRecyclerView() = with(binding) {
        rvHomeRecommend.adapter = recommendedAdapter
        rvHomePopular.adapter = popularAdapter
        rvHomeTopRated.adapter = topRatedAdapter
    }

    private fun collectUiState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is HomeUiState.Error -> showErrorDialog(state.message)
                        is HomeUiState.Loading -> {}
                        is HomeUiState.Movies -> loadMovies(state)
                    }
                }
            }
        }
    }

    private fun showErrorDialog(message: String) {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error_dialog_title))
            .setMessage(message)
            .setCancelable(false)

        val dialog = builder.create()
        dialog.show()
    }

    private fun loadMovies(state: HomeUiState.Movies) {
        popularAdapter.updateMovies(state.popularMovies)
        recommendedAdapter.updateMovies(state.recommendedMovies)
        topRatedAdapter.updateMovies(state.topRatedMovies)
    }

    private fun setListeners() = with(binding) {
        homeBtnSearch.setOnClickListener { requireContext().notImplFeature() }

        homeBtnFavorite.setOnClickListener { requireContext().notImplFeature() }
    }
}