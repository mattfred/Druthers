package com.example.druthers.ui.selection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.druthers.api.MovieRepository
import com.example.druthers.api.MovieRepositoryInterface
import com.example.druthers.models.Movie
import com.example.druthers.models.SelectionRepository
import com.example.druthers.models.SelectionRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * ViewModel for the SelectionFragment.
 * This ViewModel is responsible for loading the movie data and handling the user's interactions
 */
class SelectionViewModel(
    private val movieRepository: MovieRepositoryInterface = MovieRepository(),
    private val selectionRepository: SelectionRepositoryInterface = SelectionRepository,
    private val coroutineScope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
) : ViewModel() {

    private val loading = MutableLiveData(false)
    private val movieData: MutableLiveData<Movie?> = MutableLiveData()

    fun loadMovie() {
        loading.value = true
        // Generate a random movie id
        val title = "tt${Random.nextInt(1000000, 1299999)}"
        // retrieve the movie data from the repository
        coroutineScope.launch {
            movieRepository.getMovieById(title)?.let { movie ->
                processMovie(movie)
            }
        }
    }

    private fun processMovie(movie: Movie) {
        // If the movie is a movie and not an adult movie, then we can use it
        if (movie.type == "movie" && movie.genre.lowercase().contains("adult").not()) {
            loading.value = false
            movieData.value = movie
        } else {
            // Otherwise, try again
            loadMovie()
        }
    }

    fun getMovie() = movieData

    fun getLoading() = loading

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    fun onConfirm() {
        // Add the movie to the selection list
        movieData.value?.let {
            selectionRepository.addSelection(it)
        }
        // Load the next movie
        loadMovie()
    }

    fun onReject() {
        // Load the next movie
        loadMovie()
    }

    fun goToSelectionsList(): Boolean {
        return selectionRepository.getSelections().isNotEmpty()
    }
}