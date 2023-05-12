package com.example.moviesapp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.moviesapp.databinding.FragmentSearchBinding
import com.example.moviesapp.utils.extensions.toLowerCase
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private val adapter by lazy { SearchAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        setListeners()
        setupRecyclerView()
    }

    private fun setListeners() = binding.run {
        searchView.editText?.addTextChangedListener { editable ->
            viewModel.getSearch(editable.toString().lowercase())
        }
    }

    private fun setupRecyclerView() {

    }
}