package com.example.moviesapp.presentation.bookmarks.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.presentation.interfaces.MovieOnClickListener
import com.alvarengadev.alvaflix.view.mylist.adapter.MyListAdapter
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.FragmentBookBinding
import com.example.moviesapp.presentation.bookmarks.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class BookFragment : Fragment() {

    private var binding: FragmentBookBinding? = null

    private val viewModel: BookViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonBookBack()
        setupObservables()

    }

    private fun setupObservables() {
        viewModel.listFavorites.observe(viewLifecycleOwner, { listFavorites ->
            if (listFavorites != null && listFavorites.size > 0) {
                val myListAdapter = MyListAdapter(listFavorites)

                myListAdapter.setOnClickListener(object : MovieOnClickListener {
                    override fun onItemClick(movie: Movie) {
                        val direction =
                            BookFragmentDirections.actionBookFragmentToDetailsFragment(movie)
                        findNavController().navigate(direction)
                    }
                })

                binding?.rcyMyListFavorites?.visibility = View.VISIBLE
                binding?.containerStarted?.visibility = View.GONE

                binding?.rcyMyListFavorites?.apply {
                    adapter = myListAdapter
                    layoutManager = GridLayoutManager(context, 3)
                }
            } else {
                binding?.rcyMyListFavorites?.visibility = View.GONE
                binding?.containerStarted?.visibility = View.VISIBLE

            }
        })
    }


    private fun buttonBookBack() {
        binding?.ibMyListBack?.setOnClickListener {
            findNavController().navigate(R.id.action_bookFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}