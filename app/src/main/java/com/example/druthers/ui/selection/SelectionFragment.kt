package com.example.druthers.ui.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.druthers.R
import com.example.druthers.databinding.FragmentSelectionBinding
import com.google.android.material.snackbar.Snackbar

/**
 * Fragment to allow the user to select a movies they are interested in watching.
 */
class SelectionFragment : Fragment() {

    private var _binding: FragmentSelectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SelectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set up the click listeners for the buttons
        binding.confirm.setOnClickListener {
            viewModel.onConfirm()
        }
        binding.reject.setOnClickListener {
            viewModel.onReject()
        }
        binding.next.setOnClickListener {
            if (viewModel.goToSelectionsList()) {
                findNavController().navigate(SelectionFragmentDirections.actionSelectionFragmentToListFragment())
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.no_movie_selections_yet), Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        viewModel = ViewModelProvider(this)[SelectionViewModel::class.java]

        // Set up the observers for the view model
        viewModel.getMovie().observe(viewLifecycleOwner) { movie ->
            movie?.let {
                binding.movie = it
                loadImage(it.poster)
            }
        }

        viewModel.getError().observe(viewLifecycleOwner) {error ->
            if (error) {
                Snackbar.make(
                    binding.root,
                    "There was an error getting the movie. Please try again", Snackbar.LENGTH_SHORT
                ).show()
                viewModel.clearError()
            }

        }

        viewModel.getLoading().observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            binding.reject.isEnabled = !it
            binding.confirm.isEnabled = !it
            binding.next.isEnabled = !it
        }

        // Load the first movie
        viewModel.loadMovie()
    }

    private fun loadImage(url: String) {
        if (url.contains("http")) {
            Glide.with(this)
                .load(url)
                .into(binding.imageView)
        } else {
            Glide.with(this)
                .load("https://via.placeholder.com/300x450.png?text=No+Image")
                .into(binding.imageView)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
        _binding = null
    }
}