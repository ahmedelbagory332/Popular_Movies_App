package com.example.presentation.screens.movies_screen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.domain.models.MovieItem
import com.example.presentation.common.LoadingIndicator
import com.example.presentation.screens.movies_screen.MovieIntent
import com.example.presentation.screens.movies_screen.MovieViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun MovieList(
    isRefreshing: Boolean,
    movies: List<MovieItem>,
    viewModel: MovieViewModel,
    gridState: LazyGridState,
    onMovieClick: (MovieItem) -> Unit,
    loadFinished: Boolean
) {
    val rememberPullToRefreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        state = rememberPullToRefreshState,
        onRefresh = {
            viewModel.sendIntent(MovieIntent.UpdateIsRefreshing(isRefreshing = true))
            coroutineScope.launch {
                viewModel.sendIntent(MovieIntent.GetMovies(forceReload = true))
            }
        },
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                state = rememberPullToRefreshState,
                color = Color.Black
            )
        },
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            state = gridState,
            columns = GridCells.Fixed(2),
            content = {
                items(movies, key = { "${it.id} ${System.currentTimeMillis()}" }) { movie ->
                    MovieCard(movie = movie) {
                        onMovieClick(movie)
                    }

                }
                // Show loading indicator at the bottom if loading is in progress
                if (!loadFinished && movies.isNotEmpty()) {
                    item {
                        LoadingIndicator()
                    }
                }
            }
        )
    }
}
