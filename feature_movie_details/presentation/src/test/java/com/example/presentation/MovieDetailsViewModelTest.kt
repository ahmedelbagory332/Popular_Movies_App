package com.example.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.core_domain.NetWorkCall
import com.example.domain.models.MovieDetailsModel
import com.example.domain.use_case.GetMovieDetailsUseCase
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
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {

    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = mock()
    private val savedStateHandle = SavedStateHandle(mapOf("id" to 101L))
    private lateinit var viewModel: MovieDetailsViewModel

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
    fun `getMovieDetails should emit success state`() = runTest {
        val movieDetail = MovieDetailsModel(id = 101L, name = "Interstellar")

        whenever(getMovieDetailsUseCase.invoke(101L)).doReturn(flowOf(
            NetWorkCall.Loading(),
            NetWorkCall.Success(movieDetail)
        ))

        viewModel = MovieDetailsViewModel(getMovieDetailsUseCase, savedStateHandle)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.state.test {
            val state = awaitItem()
            assert(state.movieDetails.name == "Interstellar")
            assert(!state.isLoading)
        }
    }
}