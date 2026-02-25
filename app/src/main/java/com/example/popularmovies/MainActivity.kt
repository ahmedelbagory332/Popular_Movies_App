package com.example.popularmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.popularmovies.navigation.ScreenDestinations
import com.example.popularmovies.splash_screen.SplashScreen
import com.example.presentation.MovieDetailsScreen
import com.example.presentation.MovieScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenDestinations.SplashScreen,
    ) {
        composable<ScreenDestinations.SplashScreen> {
            SplashScreen(
                openMovieScreen = {
                    navController.navigate(ScreenDestinations.MovieListScreen) {
                        popUpTo(ScreenDestinations.SplashScreen) { inclusive = true }
                    }
                },
            )
        }
        composable<ScreenDestinations.MovieListScreen> {
            MovieScreen { id ->
                navController.navigate(ScreenDestinations.MovieDetailScreen(id))
            }
        }
        composable<ScreenDestinations.MovieDetailScreen> {
            MovieDetailsScreen {
                navController.popBackStack()
            }
        }
    }
}