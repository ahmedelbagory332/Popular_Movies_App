package com.example.domain.use_case

import com.example.core_domain.base.BaseUseCase
import com.example.domain.models.MovieDetailsModel
import com.example.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) : BaseUseCase<MovieDetailsModel, Long>() {

    override suspend fun execute(params: Long): MovieDetailsModel {
        return repository.getMovieDetails(movieId = params.toString())
    }
}