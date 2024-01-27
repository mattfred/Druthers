package com.example.druthers.ui.list

import androidx.lifecycle.ViewModel
import com.example.druthers.models.Movie
import com.example.druthers.models.SelectionRepository
import com.example.druthers.models.SelectionRepositoryInterface

/**
 * ViewModel for the [ListFragment] screen.
 * The ViewModel works with the [SelectionRepository] to display the list of movie selections.
 */
class ListViewModel(
    private val selectionRepository: SelectionRepositoryInterface = SelectionRepository,
) : ViewModel() {
    fun getMovies(): List<Movie> {
        return selectionRepository.getSelections()
    }
}