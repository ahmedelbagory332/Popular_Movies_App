package com.example.data.repositoryImpl

import com.example.data.api.MovieApi
import com.example.data.mapper.toModel
import com.example.data.utils.safeApiCall
import com.example.domain.models.MovieDetailsModel
import com.example.domain.models.MovieModel
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getMovieDetails(movieId: String): MovieDetailsModel {
        return safeApiCall { api.getMovieDetails(id = movieId).toModel() }
    }

    override suspend fun getMovie(page: Int): MovieModel {
        return safeApiCall { api.getMovies(page = page).toModel() }
    }
}