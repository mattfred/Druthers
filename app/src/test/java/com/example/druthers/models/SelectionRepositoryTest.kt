package com.example.druthers.models

import org.junit.Test


class SelectionRepositoryTest {
    private val selection =
        Movie(
            "The Matrix",
            "n/a",
            "Sci-FI",
            "Movie",
            "1999",
            "PG-13",
            "An exciting movie"
        )

    @Test
    fun addGetSelectionTest() {
        SelectionRepository.addSelection(selection)
        assert(SelectionRepository.getSelections().contains(selection))
    }

}