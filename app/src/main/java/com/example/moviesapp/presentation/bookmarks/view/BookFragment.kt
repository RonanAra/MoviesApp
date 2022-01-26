package com.example.moviesapp.presentation.bookmarks.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentBookBinding


class BookFragment : Fragment() {

    private var binding: FragmentBookBinding? = null


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

        binding?.ibMyListBack?.setOnClickListener {
            findNavController().navigate(R.id.action_bookFragment_to_homeFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}