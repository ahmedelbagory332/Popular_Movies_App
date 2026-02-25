package com.example.data

import com.example.core.network.api.ApiServices
import com.example.core.network.models.MovieItemNetworkModel
import com.example.core.network.models.MovieNetworkModel
import com.example.data.mapper.toModel
import com.example.data.repositoryImpl.MovieRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import java.io.IOException
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    private val api = mock<ApiServices>()
    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setup() {
        repository = MovieRepositoryImpl(api)
    }

    @Test
    fun `getMovie returns data successfully`() = runTest {
        val mockMovieItem = MovieItemNetworkModel(
            adult = false,
            backdropPath = "/backdrop.jpg",
            genreIds = listOf(28, 12),
            id = 1L,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "Test movie overview",
            popularity = 123.4,
            posterPath = "/poster.jpg",
            releaseDate = "2024-01-01",
            title = "Test Movie",
            video = false,
            voteAverage = 8.5,
            voteCount = 1000L
        )

        val mockResponse = MovieNetworkModel(
            page = 1,
            movieList = listOf(mockMovieItem),
            totalPages = 10,
            totalResults = 100
        )
        whenever(api.getMovies(page = 1)).thenReturn(mockResponse)

        val result = repository.getMovie(1)

        assertEquals(mockResponse.toModel(), result)
    }

    @Test
    fun `getMovie throws Network error`() = runTest {
        whenever(api.getMovies(page = 1)).thenAnswer { throw IOException("No internet") }

        val exception = assertFailsWith<Exception> {
            repository.getMovie(1)
        }
        assertTrue(exception.message!!.contains("Network error"))
    }

    @Test
    fun `getMovie throws Server error`() = runTest {
        val httpException = mock<HttpException>()
        whenever(httpException.code()).thenReturn(500)
        whenever(httpException.message()).thenReturn("Internal Server Error")
        whenever(api.getMovies(page = 1)).thenAnswer { throw httpException }

        val exception = assertFailsWith<Exception> {
            repository.getMovie(1)
        }
        assertTrue(exception.message!!.contains("Server error"))
    }
}