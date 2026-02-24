package com.example.data

import com.example.data.utils.safeApiCall
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class SafeApiCallTest {

    @Test
    fun `safeApiCall returns result`() = runTest {
        val result = safeApiCall { "OK" }
        assertEquals("OK", result)
    }

    @Test
    fun `safeApiCall throws network error`() = runTest {
        val exception = assertThrows(Exception::class.java) {
            runBlocking { safeApiCall<String> { throw IOException() } }
        }
        assertTrue(exception.message!!.contains("Network error"))
    }

    @Test
    fun `safeApiCall throws http error`() = runTest {
        val responseBody = "Server error".toResponseBody()
        val httpException = HttpException(
            Response.error<String>(500, responseBody)
        )

        val exception = assertThrows(Exception::class.java) {
            runBlocking { safeApiCall<String> { throw httpException } }
        }

        assertTrue(exception.message!!.contains("Server error"))
    }
}