package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domain.Destinations
import com.example.presentation.screens.movies_screen.MovieScreen
import com.example.presentation.screens.splash_screen.SplashScreen
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
        startDestination = Destinations.SplashScreen,
    ) {
        composable<Destinations.SplashScreen> {
            SplashScreen(
                openMovieScreen = {
                    navController.navigate(Destinations.MovieListScreen) {
                        popUpTo(Destinations.SplashScreen) { inclusive = true }
                    }
                },
            )
        }
        composable<Destinations.MovieListScreen> {
            MovieScreen{ movie ->
                navController.navigate(Destinations.MovieDetailScreen(movie.id))
            }
        }
    }
}