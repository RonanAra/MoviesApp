package com.example.moviesapp.presentation.search.view

import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentSearchBinding
import com.example.moviesapp.extensions.toLowerCase
import com.example.moviesapp.presentation.base.BaseFragment
import com.example.moviesapp.presentation.search.adapter.SearchAdapter
import com.example.moviesapp.presentation.search.viewmodel.SearchViewModel
import com.example.moviesapp.utils.Command
import com.example.moviesapp.utils.ConstantsApp.Api.KEY_BUNDLE_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {

    private var binding: FragmentSearchBinding? = null
    override var command: MutableLiveData<Command> = MutableLiveData()

    private val viewModel: SearchViewModel by viewModel()

    private val adapter = SearchAdapter { movie ->
        val bundle = Bundle()
        movie.id?.let { bundle.putInt(KEY_BUNDLE_ID, it) }
        findNavController().navigate(
            R.id.action_searchFragment_to_detailsFragment,
            bundle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.command = command
        searchComponent()
        setupRecyclerView()

    }

    private fun searchComponent() {
        binding?.searchView?.editText?.addTextChangedListener { editable ->
            viewModel.getSearch(toLowerCase(editable.toString()))
        }
    }

    private fun setupRecyclerView() {
        viewModel.onSuccessSearch.observe(viewLifecycleOwner, { listMovies ->
            adapter.submitList(listMovies)
            binding?.rvSearchMovies?.adapter = adapter
            binding?.rvSearchMovies?.layoutManager = LinearLayoutManager(context)

        })

    }
        override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}