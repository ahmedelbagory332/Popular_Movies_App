package com.example.data.repositoryImpl

import com.example.core.network.api.ApiServices
import com.example.core.network.utils.safeApiCall
import com.example.data.mapper.toModel
import com.example.domain.models.MovieDetailsModel
import com.example.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val api: ApiServices
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: String): MovieDetailsModel {
        return safeApiCall { api.getMovieDetails(id = movieId).toModel() }
    }
}