package com.example.core_domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<Type, Params> {

    abstract suspend fun execute(params: Params): Type

    operator fun invoke(params: Params): Flow<NetWorkCall<Type>> = flow {
        emit(NetWorkCall.Loading())
        try {
            val result = execute(params)
            emit(NetWorkCall.Success(result))
        } catch (e: Exception) {
            emit(NetWorkCall.Error(e.message ?: "Unknown error"))
        }
    }
}