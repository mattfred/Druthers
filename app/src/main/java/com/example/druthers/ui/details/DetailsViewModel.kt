package com.example.druthers.ui.details

import androidx.lifecycle.ViewModel
import com.example.druthers.models.Movie

/**
 * ViewModel for the [DetailsFragment] screen.
 * The ViewModel works with the [DetailsFragment] to show the current movie selected.
 */
class DetailsViewModel : ViewModel() {
    private var movie: Movie? = null

    fun setMovie(movieObject: Movie) {
        movie = movieObject
    }

    fun getMovie(): Movie? {
        return movie
    }
}