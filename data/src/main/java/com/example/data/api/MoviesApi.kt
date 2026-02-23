package com.example.data.api

import com.example.data.BuildConfig
import com.example.data.models.GenreNetWorkModel
import com.example.data.models.MovieDetailsNetWorkModel
import com.example.data.models.MovieNetworkModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): GenreNetWorkModel

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int,
    ): MovieNetworkModel

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Path("id") id: String,
    ): MovieDetailsNetWorkModel
}