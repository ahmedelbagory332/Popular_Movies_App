package com.example.core.network.api

import com.example.core.BuildConfig
import com.example.core.network.models.MovieDetailsNetWorkModel
import com.example.core.network.models.MovieNetworkModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int,
    ): MovieNetworkModel

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): MovieDetailsNetWorkModel
}