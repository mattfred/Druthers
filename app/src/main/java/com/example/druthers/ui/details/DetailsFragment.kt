package com.example.druthers.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.druthers.databinding.FragmentDetailsBinding

/**
 * Fragment used to show movie details.
 */
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        viewModel.setMovie(DetailsFragmentArgs.fromBundle(requireArguments()).movieObject)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // load the movie object into the layout
        binding.movieObject = viewModel.getMovie()
        // load the image into the layout
        loadImage(viewModel.getMovie()?.poster ?: "")
    }

    private fun loadImage(url: String) {
        if (url.contains("http")) {
            Glide.with(this)
                .load(url)
                .into(binding.imageView)
        } else {
            // if not a valid url, load a placeholder image
            Glide.with(this)
                .load("https://via.placeholder.com/300x450.png?text=No+Image")
                .into(binding.imageView)
        }
    }

}