package com.example.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.core_domain.NetWorkCall
import com.example.domain.models.MovieDetailsModel
import com.example.domain.use_case.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    val savedState: SavedStateHandle
) : BaseViewModel<MovieDetailsState, MovieDetailsIntent>(MovieDetailsState()) {

    init {
        sendIntent(MovieDetailsIntent.GetMovieDetails)
    }

    override fun sendIntent(intent: MovieDetailsIntent) {
        when (intent) {
            is MovieDetailsIntent.GetMovieDetails -> getMovieDetails()
        }
    }

    private fun getMovieDetails() {
        val id = savedState.get<Long>("id") ?: 0L
        getMovieDetailsUseCase(id).onEach { result ->
            when (result) {
                is NetWorkCall.Success -> {
                    updateState {
                        it.copy(
                            movieDetails = result.data ?: MovieDetailsModel(),
                            isLoading = false,
                            error = ""
                        )
                    }
                }

                is NetWorkCall.Error -> updateState {
                    it.copy(
                        error = result.message ?: "An unexpected error happened",
                        isLoading = false,
                        movieDetails = MovieDetailsModel(),
                    )
                }

                is NetWorkCall.Loading -> updateState {
                    it.copy(
                        isLoading = true,
                        movieDetails = MovieDetailsModel(),
                        error = ""
                    )
                }
            }
        }.launchIn(viewModelScope)

    }

}