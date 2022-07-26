package com.example.mvi.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S,A>(State:S) {
    private val viewState: MutableStateFlow<S> = MutableStateFlow(State)
    private val vState:StateFlow<S> = viewState.asStateFlow()
    val state:S
    get() = vState.value
    abstract fun onAction(action:A)


    fun setState(state:S){
        viewState.value=state
    }

    @Composable
    fun bind(): State<S> {
        return vState.collectAsState()
    }
}


