package com.example.domain.use_case

import com.example.domain.BaseUseCase
import com.example.domain.models.MovieModel
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<MovieModel, Int>() {

    override suspend fun execute(params: Int): MovieModel {
        return repository.getMovie(page = params)
    }
}