package com.example.druthers.ui.selection

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.druthers.mocks.MockMovieRepository
import com.example.druthers.mocks.MockMovieSelectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class SelectionViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun loadMovieTest() = runBlocking {
        val movieRepository = MockMovieRepository()
        val viewModel = SelectionViewModel(
            movieRepository = movieRepository,
            selectionRepository = MockMovieSelectionRepository()
        )
        viewModel.loadMovie()
        assert(viewModel.getMovie().value != null)
    }

    @Test
    fun getSelectionsTest() = runBlocking {
        val selectionRepository = MockMovieSelectionRepository()
        val viewModel = SelectionViewModel(
            movieRepository = MockMovieRepository(),
            selectionRepository = selectionRepository
        )
        viewModel.loadMovie()
        assert(viewModel.getMovie().value != null)
        viewModel.onConfirm()
        assert(selectionRepository.getSelections().isNotEmpty())
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}