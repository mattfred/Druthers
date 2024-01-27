package com.example.druthers.models

interface SelectionRepositoryInterface {
    fun addSelection(selection: Movie)
    fun getSelections(): List<Movie>
}

/**
 * Singleton class that holds the user's movie selections.
 */
object SelectionRepository : SelectionRepositoryInterface {
    private val selections = mutableListOf<Movie>()

    override fun addSelection(selection: Movie) {
        selections.add(selection)
    }

    override fun getSelections(): List<Movie> {
        return selections
    }
}