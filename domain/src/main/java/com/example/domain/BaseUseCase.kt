package com.example.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

abstract class BaseUseCase<Type, Params> {

    protected abstract suspend fun execute(params: Params): Type

    operator fun invoke(params: Params): Flow<NetWorkCall<Type>> = flow {
        emit(NetWorkCall.Loading())
        try {
            val result = execute(params)
            emit(NetWorkCall.Success(result))
        } catch (e: IOException) {
            emit(NetWorkCall.Error("IO error: ${e.localizedMessage}"))
        } catch (e: Exception) {
            emit(NetWorkCall.Error("Unexpected error: ${e.localizedMessage}"))
        }
    }
}