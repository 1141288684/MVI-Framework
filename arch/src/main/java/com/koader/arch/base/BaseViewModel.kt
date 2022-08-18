package com.koader.arch.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<S,A>(private val view:BaseView, State:S) :LifecycleOwner{
    private val viewState: MutableStateFlow<S> = MutableStateFlow(State)
    private val vState:StateFlow<S> = viewState.asStateFlow()

    private val state:S
    get() = vState.value

    abstract fun onAction(action:A)

    fun setState(s:(S)->Unit){
        viewState.value=state.apply(s)
    }

    @Composable
    fun bind(): State<S> {
        return vState.collectAsState()
    }


    override fun getLifecycle(): Lifecycle {
        return view.getLifeCycle()
    }
}


