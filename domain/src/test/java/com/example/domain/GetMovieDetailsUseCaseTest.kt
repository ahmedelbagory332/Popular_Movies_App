package com.example.domain

import com.example.domain.repository.MovieRepository
import com.example.domain.use_case.GetMovieDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieDetailsUseCaseTest {

    private val repository: MovieRepository = mock()
    private lateinit var useCase: GetMovieDetailsUseCase

    @Before
    fun setup() {
        useCase = GetMovieDetailsUseCase(repository)
    }

    @Test
    fun `execute returns movie details`() = runTest {
        whenever(repository.getMovieDetails("1"))
            .thenReturn(MovieTestFactory.movieDetails())

        val result = useCase.execute(1)

        assertEquals(1, result.id)
        assertEquals("Movie Details", result.name)
    }
}