package com.example.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.domain.BaseUseCase
import com.example.domain.models.MovieItem
import com.example.domain.pagingSource.MoviesPagingSource
import com.example.domain.repository.MovieRepository

class GetMoviesUseCase (
    private val repository: MovieRepository
) : BaseUseCase<Pager<Int, MovieItem>, Unit>() {

    override suspend fun execute(params: Unit): Pager<Int, MovieItem> {
        val getMovies  = Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                MoviesPagingSource(repository)
            }
        )
        return getMovies
    }
}