package com.example.data.repositoryImpl

import com.example.data.api.MovieApi
import com.example.data.mapper.toModel
import com.example.domain.models.GenreModel
import com.example.domain.models.MovieDetailsModel
import com.example.domain.models.MovieModel
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {
    override suspend fun getGenre(): GenreModel {
        return api.getGenres().toModel()
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailsModel {
        return api.getMovieDetails(id = movieId).toModel()
    }

    override suspend fun getMovie(page: Int): MovieModel {
        return api.getMovies(page = page).toModel()
    }
}