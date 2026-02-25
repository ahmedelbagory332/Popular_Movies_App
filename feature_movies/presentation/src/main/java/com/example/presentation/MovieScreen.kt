package com.example.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.design_system.colors.PurpleGrey40
import com.example.design_system.components.ChangeSystemUiColors
import com.example.design_system.components.ErrorHolder
import com.example.design_system.components.LoadingMoviesIndicator
import com.example.design_system.components.SearchBar
import com.example.presentation.components.MovieList

@Composable
fun MovieScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onMovieClick: (Long) -> Unit
) {
    val ctx = LocalContext.current
    val uiState by viewModel.state.collectAsState()
    val gridState = rememberLazyGridState()

    ChangeSystemUiColors()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PurpleGrey40)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        when {
            uiState.isLoading && uiState.movies.isEmpty() -> {
                LoadingMoviesIndicator(ctx)
            }

            uiState.error.isNotEmpty() -> {
                ErrorHolder(text = uiState.error) {
                    viewModel.sendIntent(MovieIntent.GetMovies(forceReload = true))
                }
            }

            else -> {
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    SearchBar(
                        searchValue = uiState.search,
                        onValueChanged = {
                            viewModel.sendIntent(MovieIntent.OnSearchChanged(it))
                        },
                        onClearClick = {
                            viewModel.sendIntent(MovieIntent.ClearSearch)
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Popular Movies \uD83D\uDD25",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        ),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    MovieList(
                        isRefreshing = uiState.isRefreshing,
                        movies = uiState.movies,
                        viewModel = viewModel,
                        gridState = gridState,
                        onMovieClick = onMovieClick,
                        loadFinished = uiState.loadFinished
                    )
                    LaunchedEffect(gridState) {
                        snapshotFlow { gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                            .collect { lastVisible ->
                                if (lastVisible != null && lastVisible >= uiState.movies.size - 1 && !uiState.loadFinished && uiState.search.isEmpty()) {
                                    viewModel.sendIntent(MovieIntent.GetMovies(forceReload = false))
                                }
                            }
                    }

                }
            }
        }

    }
}