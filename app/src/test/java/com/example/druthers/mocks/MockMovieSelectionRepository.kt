package com.example.druthers.mocks

import com.example.druthers.models.Movie
import com.example.druthers.models.SelectionRepositoryInterface

class MockMovieSelectionRepository: SelectionRepositoryInterface {

    private val selections = mutableListOf<Movie>()
    override fun addSelection(selection: Movie) {
        selections.add(selection)
    }

    override fun getSelections(): List<Movie> {
        return selections
    }
}