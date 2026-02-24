package com.example.domain

import com.example.domain.repository.MovieRepository
import com.example.domain.use_case.GetMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetMoviesUseCaseTest {

    private val repository: MovieRepository = mock()
    private lateinit var useCase: GetMoviesUseCase

    @Before
    fun setup() {
        useCase = GetMoviesUseCase(repository)
    }

    @Test
    fun `execute returns movie list`() = runTest {
        whenever(repository.getMovie(1)).thenReturn(MovieTestFactory.movieModel())

        val result = useCase.execute(1)

        assertEquals(1, result.moveList.size)
        assertEquals("Movie Title", result.moveList.first().name)
    }
}