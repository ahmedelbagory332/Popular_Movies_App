package com.example.presentation.screens.movies_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.NetWorkCall
import com.example.domain.use_case.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MovieState())
    val state = _state.asStateFlow()

    init {
        sendIntent(MovieIntent.GetMovies(forceReload = true))
    }

    fun sendIntent(intent: MovieIntent) {
        viewModelScope.launch {
            when (intent) {
                is MovieIntent.GetMovies -> getMovies(forceReload = intent.forceReload)
                is MovieIntent.OpenMovieDetails -> {}
                is MovieIntent.OnSearchChanged -> onSearchChanged(intent.search)
                is MovieIntent.ClearSearch -> clearSearch()
                is MovieIntent.UpdateIsRefreshing -> updateIsRefreshing(intent.isRefreshing)
            }
        }
    }

    private fun getMovies(forceReload: Boolean = false) {
        if (state.value.isLoading) return
        if (forceReload) currentPage = 1
        getMoviesUseCase(currentPage).onEach { result ->
            _state.value = when (result) {
                is NetWorkCall.Success -> {
                    val newMovies = result.data?.moveList ?: emptyList()
                    val list = if (currentPage == 1)
                        newMovies
                    else
                        _state.value.movies + newMovies
                    currentPage++
                    _state.value.copy(
                        movies = list,
                        loadFinished = newMovies.isEmpty(),
                        isLoading = false,
                        isRefreshing = false,
                        error = ""
                    )
                }

                is NetWorkCall.Error -> _state.value.copy(
                    error = result.message ?: "An unexpected error happened",
                    movies = emptyList(),
                    isLoading = false,
                    loadFinished = true,
                    isRefreshing = false
                )

                is NetWorkCall.Loading -> {
                    if (forceReload) _state.value.copy(
                        isLoading = true,
                        error = "",
                        movies = emptyList(),
                    ) else _state.value
                }

            }
        }.launchIn(viewModelScope)

    }

    private fun onSearchChanged(search: String) {
        _state.value = _state.value.copy(search = search)
        val filteredMovies = _state.value.movies.filter { it.name.contains(search) }
        _state.value = _state.value.copy(
            movies = filteredMovies,
            isLoading = false,
            loadFinished = true,
            isRefreshing = false
        )
        if (search.isEmpty())
            sendIntent(MovieIntent.GetMovies(forceReload = true))
    }

    private fun clearSearch() {
        _state.value = _state.value.copy(search = "")
        sendIntent(MovieIntent.GetMovies(forceReload = true))
    }

    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _state.value = _state.value.copy(isRefreshing = isRefreshing)
    }

    companion object {
        private var currentPage = 1
    }
}