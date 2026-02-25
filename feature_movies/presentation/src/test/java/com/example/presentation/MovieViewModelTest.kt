package com.example.presentation

import app.cash.turbine.test
import com.example.core_domain.base.NetWorkCall
import com.example.domain.models.MovieItem
import com.example.domain.models.MovieModel
import com.example.domain.use_case.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModelTest {

    // 1. Initialize the mock using mock() from mockito-kotlin
    private val getMoviesUseCase: GetMoviesUseCase = mock()
    private lateinit var viewModel: MovieViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `send GetMovies intent should update state with movies on success`() = runTest {
        // 1. Setup mock BEFORE initializing ViewModel
        val mockMovies = MovieModel(moveList = listOf(MovieItem(id = 1, name = "Inception")))
        whenever(getMoviesUseCase.invoke(any())).doReturn(flowOf(
            NetWorkCall.Loading(),
            NetWorkCall.Success(mockMovies)
        ))

        // 2. Initialize ViewModel
        viewModel = MovieViewModel(getMoviesUseCase)

        // 3. IMPORTANT: Tell the dispatcher to execute the pending coroutines from init{}
        testDispatcher.scheduler.advanceUntilIdle()

        // 4. Then Assert
        viewModel.state.test {
            val state = awaitItem()
            assert(state.movies.any { it.name == "Inception" })
            assert(!state.isLoading)
        }
    }
}