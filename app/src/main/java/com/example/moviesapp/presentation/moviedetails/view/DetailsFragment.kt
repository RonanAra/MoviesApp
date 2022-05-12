package com.example.moviesapp.presentation.moviedetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.FragmentDetailsBinding
import com.example.moviesapp.extensions.createToast
import com.example.moviesapp.presentation.moviedetails.viewmodel.DetailsViewModel
import com.example.moviesapp.utils.Command
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null

    private val args: DetailsFragmentArgs by navArgs()

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

        val movie = args.movie


        setupArgsById()
        buttonBack()
        showIconMyList(movie)
    }

    private fun setupArgsById() {
        val movie = args.movie

        binding?.let { binding ->
            activity?.let { activity ->
                Glide
                    .with(activity)
                    .load(movie.backdrop_path)
                    .error(R.drawable.place_holder)
                    .into(binding.ivDetailsImage)

                binding.tvTitleDetails.text = movie.title
                binding.tvOverview.text = movie.overview
            }
        }

    }

    private fun buttonBack() {
        binding?.ibDetailsBack?.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }
    }


    private fun showIconMyList(movie: Movie) {

        viewModel.isMovieFavorite(movie)
        viewModel.isMovieFavoriteData.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding?.btnLinearDetailsMyList?.setOnClickListener(deleteMovieFavorite(movie))
                binding?.ivDetailsIconMyList?.setImageResource(R.drawable.ic_outline_favorite_red)
            } else {
                binding?.btnLinearDetailsMyList?.setOnClickListener(addMovieFavorite(movie))
                binding?.ivDetailsIconMyList?.setImageResource(R.drawable.ic_favorite_border_24px)
            }
        }
    }


    private fun addMovieFavorite(movie: Movie) = View.OnClickListener {
        viewModel.insertMovieFavorite(movie)
        binding?.ivDetailsIconMyList?.setImageResource(R.drawable.ic_outline_favorite_red)
        requireContext().createToast("Add in My List")
    }

    private fun deleteMovieFavorite(movie: Movie) = View.OnClickListener {
        viewModel.deleteMovieFavorite(movie)
        binding?.ivDetailsIconMyList?.setImageResource(R.drawable.ic_favorite_border_24px)
        requireContext().createToast("Delete in My List")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}