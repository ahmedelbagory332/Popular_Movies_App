package com.example.presentation

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.core_domain.NetWorkCall
import com.example.domain.use_case.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : BaseViewModel<MovieState, MovieIntent>(MovieState()) {

    private var getMoviesJob: Job? = null

    init {
        sendIntent(MovieIntent.GetMovies(forceReload = true))
    }

    override fun sendIntent(intent: MovieIntent) {
        when (intent) {
            is MovieIntent.GetMovies -> getMovies(forceReload = intent.forceReload)
            is MovieIntent.OpenMovieDetails -> {}
            is MovieIntent.OnSearchChanged -> onSearchChanged(intent.search)
            is MovieIntent.ClearSearch -> clearSearch()
            is MovieIntent.UpdateIsRefreshing -> updateIsRefreshing(intent.isRefreshing)
            is MovieIntent.OnRefreshing -> {
                updateIsRefreshing(true)
                getMovies(forceReload = true)
            }
        }
    }

    private fun getMovies(forceReload: Boolean = false) {
        if (state.value.isLoading) return
        getMoviesJob?.cancel()
        if (forceReload) {
            currentPage = 1
            updateState { it.copy(movies = emptyList()) }
        }
        getMoviesJob = getMoviesUseCase(currentPage).onEach { result ->
            when (result) {
                is NetWorkCall.Success -> {
                    val newMovies = result.data?.moveList ?: emptyList()
                    val list = if (currentPage == 1)
                        newMovies
                    else
                        state.value.movies + newMovies

                    updateState {
                        it.copy(
                            movies = list,
                            allMovies = list,
                            loadFinished = newMovies.isEmpty(),
                            isLoading = false,
                            isRefreshing = false,
                            error = ""
                        )
                    }
                    currentPage++
                }

                is NetWorkCall.Error -> updateState {
                    it.copy(
                        error = result.message ?: "An unexpected error happened",
                        movies = emptyList(),
                        isLoading = false,
                        loadFinished = true,
                        isRefreshing = false
                    )
                }

                is NetWorkCall.Loading -> {
                    if (forceReload)
                        updateState {
                            it.copy(
                                isLoading = true,
                                error = "",
                                movies = emptyList(),
                            )
                        }
                }
            }
        }.launchIn(viewModelScope)


    }

    private fun onSearchChanged(search: String) {
        updateState {
            it.copy(search = search)
        }
        val currentAllMovies = state.value.allMovies
        val filteredMovies = if (search.isEmpty()) {
            currentAllMovies
        } else {
            currentAllMovies.filter { it.name.contains(search, ignoreCase = true) }
        }
        updateState {
            it.copy(
                movies = filteredMovies,
                isLoading = false,
                loadFinished = true,
                isRefreshing = false
            )
        }
        if (search.isEmpty())
            sendIntent(MovieIntent.GetMovies(forceReload = true))
    }

    private fun clearSearch() {
        updateState {
            it.copy(
                search = ""
            )
        }
        sendIntent(MovieIntent.GetMovies(forceReload = true))
    }

    private fun updateIsRefreshing(isRefreshing: Boolean) {
        updateState {
            it.copy(
                isRefreshing = isRefreshing
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        getMoviesJob?.cancel()
    }

    companion object {
        private var currentPage = 1
    }
}