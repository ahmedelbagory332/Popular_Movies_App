package com.example.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State : ViewState, Intent : ViewIntent>(
    initialState: State
) : ViewModel() {

    protected abstract val mutableStateFlow: MutableStateFlow<State>

    val state: StateFlow<State>
        get() = mutableStateFlow

    abstract fun sendIntent(intent: Intent)


    protected fun updateState(reducer: (State) -> State) {
        mutableStateFlow.update { state -> reducer(state) }
    }
}