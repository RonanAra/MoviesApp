package com.example.moviesapp.features.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.features.viewmodel.HomeViewModel
import com.example.moviesapp.utils.Command

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
            viewModel.command = command

            viewModel.getPopular()
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
                                layoutManager = GridLayoutManager(context,2)
                                adapter = popularAdapter

                            }
                        }
                    }

                }
            })
        }


        viewModel.onErrorPopular.observe(viewLifecycleOwner, {
            it
        })
    }



override fun onDestroy() {
    super.onDestroy()
    binding = null
}

var command: MutableLiveData<Command> = MutableLiveData()

}
