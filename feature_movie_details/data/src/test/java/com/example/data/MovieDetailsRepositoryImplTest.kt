package com.example.data

import com.example.core.network.api.ApiServices
import com.example.core.network.models.MovieDetailsNetWorkModel
import com.example.data.mapper.toModel
import com.example.data.repositoryImpl.MovieDetailsRepositoryImpl
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
class MovieDetailsRepositoryImplTest {

    private val api = mock<ApiServices>()
    private lateinit var repository: MovieDetailsRepositoryImpl

    @Before
    fun setup() {
        repository = MovieDetailsRepositoryImpl(api)
    }

    @Test
    fun `getMovie returns data successfully`() = runTest {
        val mockMovieDetails = MovieDetailsNetWorkModel(
            adult = false,
            backdropPath = "/backdrop.jpg",
            id = 1L,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "Test movie overview",
            popularity = 123.4,
            posterPath = "/poster.jpg",
            releaseDate = "2024-01-01",
            title = "Test Movie",
            video = false,
            voteAverage = 7.8,
            voteCount = 1000,
            genres = emptyList(),
            budget = 1000000,
            homepage = "https://www.example.com",
            imdbId = "tt1234567",
            revenue = 2000000,
            runtime = 120,
            status = "Released",
            tagline = "Test tagline",
            belongsToCollection = null,
            productionCompanies = emptyList(),
            productionCountries = emptyList(),
            spokenLanguages = emptyList()
        )


        whenever(api.getMovieDetails("1")).thenReturn(mockMovieDetails)

        val result = repository.getMovieDetails("1")

        assertEquals(mockMovieDetails.toModel(), result)
    }

    @Test
    fun `getMovie throws Network error`() = runTest {
        whenever(api.getMovieDetails("1")).thenAnswer { throw IOException("No internet") }

        val exception = assertFailsWith<Exception> {
            repository.getMovieDetails("1")
        }
        assertTrue(exception.message!!.contains("Network error"))
    }

    @Test
    fun `getMovie throws Server error`() = runTest {
        val httpException = mock<HttpException>()
        whenever(httpException.code()).thenReturn(500)
        whenever(httpException.message()).thenReturn("Internal Server Error")
        whenever(api.getMovieDetails("1")).thenAnswer { throw httpException }

        val exception = assertFailsWith<Exception> {
            repository.getMovieDetails("1")
        }
        assertTrue(exception.message!!.contains("Server error"))
    }
}