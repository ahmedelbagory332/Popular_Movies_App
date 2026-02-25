package com.example.domain

import com.example.core_domain.NetWorkCall
import com.example.domain.repository.MovieRepository
import com.example.domain.use_case.GetMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class BaseUseCaseTest {

    private val repository: MovieRepository = mock()
    private lateinit var useCase: GetMoviesUseCase

    @Before
    fun setup() {
        useCase = GetMoviesUseCase(repository)
    }

    @Test
    fun `invoke emits Loading then Success`() = runTest {
        whenever(repository.getMovie(1)).thenReturn(MovieTestFactory.movieModel())

        val results = useCase(1).toList()

        assertTrue(results[0] is NetWorkCall.Loading)
        assertTrue(results[1] is NetWorkCall.Success)
    }
}