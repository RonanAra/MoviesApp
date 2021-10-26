package com.example.moviesapp.features.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adpter.PopularAdapter
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.features.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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

        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]

            viewModel.getPopular()
            viewModel.getNowPlaying()

            setupObservables()
        }

    }

    private fun setupObservables() {
        activity?.let {
            viewModel.onSuccessPopular.observe(viewLifecycleOwner, {
                it?.let {
                    val popularAdapter = PopularAdapter(
                        listaMovies = it
                    )

                    binding?.let {
                        with(it) {
                            rvHomePopular.apply {
                                layoutManager = LinearLayoutManager(context)
                                adapter = popularAdapter

                            }
                        }
                    }

                }
            })
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}