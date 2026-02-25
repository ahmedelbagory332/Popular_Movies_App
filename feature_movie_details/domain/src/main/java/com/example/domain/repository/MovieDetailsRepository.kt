package com.example.domain.repository

import com.example.domain.models.MovieDetailsModel

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: String): MovieDetailsModel
}