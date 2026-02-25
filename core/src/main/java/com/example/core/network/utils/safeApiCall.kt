package com.example.core.network.utils

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): T {
    try {
        return apiCall()
    } catch (e: IOException) {
        // No internet / network issue
        throw Exception("Network error: Please check your internet connection")
    } catch (e: HttpException) {
        // HTTP error from server
        val code = e.code()
        val message = e.response()?.errorBody()?.string() ?: e.message()
        throw Exception("Server error: $code - $message")
    } catch (e: Exception) {
        // Unknown errors
        throw Exception("Unknown error: ${e.localizedMessage}")
    }
}