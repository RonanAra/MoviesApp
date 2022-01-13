package com.example.moviesapp.presentation.moviedetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentDetailsBinding
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.presentation.moviedetails.viewmodel.DetailsViewModel
import com.example.moviesapp.utils.Command
import com.example.moviesapp.utils.ConstantsApp.Api.KEY_BUNDLE_ID
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null
    private val movieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_ID) ?: -1
    }

    var command: MutableLiveData<Command> = MutableLiveData()
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.command = command

        viewModel.getMovieById(movieId)
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.onSuccessMovieById.observe(viewLifecycleOwner, {
            it
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}