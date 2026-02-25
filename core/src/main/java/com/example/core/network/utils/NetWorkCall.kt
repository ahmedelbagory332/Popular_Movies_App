package com.example.core.network.utils

sealed class NetWorkCall <T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : NetWorkCall<T>(data)
    class Error<T>(message: String, data: T? = null) : NetWorkCall<T>(data, message)
    class Loading<T>(data: T? = null) : NetWorkCall<T>(data)
}