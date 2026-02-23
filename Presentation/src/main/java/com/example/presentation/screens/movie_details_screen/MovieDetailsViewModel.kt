package com.example.presentation.screens.movie_details_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.NetWorkCall
import com.example.domain.models.MovieDetailsModel
import com.example.domain.use_case.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    val savedState: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

    init {
        sendIntent(MovieDetailsIntent.GetMovieDetails)
    }

    fun sendIntent(intent: MovieDetailsIntent) {
        viewModelScope.launch {
            when (intent) {
                is MovieDetailsIntent.GetMovieDetails -> getMovieDetails()
            }
        }
    }

    private fun getMovieDetails() {
        val id = savedState.get<Long>("id") ?: 0L
        getMovieDetailsUseCase(id).onEach { result ->
            _state.value = when (result) {
                is NetWorkCall.Success -> {
                    _state.value.copy(
                        movieDetails = result.data ?: MovieDetailsModel(),
                        isLoading = false,
                        error = ""
                    )
                }

                is NetWorkCall.Error -> _state.value.copy(
                    error = result.message ?: "An unexpected error happened",
                    isLoading = false,
                    movieDetails = MovieDetailsModel(),
                )

                is NetWorkCall.Loading -> _state.value.copy(
                    isLoading = true,
                    movieDetails = MovieDetailsModel(),
                    error = ""
                )
            }
        }.launchIn(viewModelScope)

    }

}