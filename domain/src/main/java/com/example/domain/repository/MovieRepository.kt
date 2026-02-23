package com.example.domain.repository

import com.example.domain.models.MovieDetailsModel
import com.example.domain.models.MovieModel

interface MovieRepository {
    suspend fun getMovieDetails(movieId: String): MovieDetailsModel
    suspend fun getMovie(page: Int): MovieModel
}