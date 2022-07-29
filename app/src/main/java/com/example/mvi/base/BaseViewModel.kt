package com.example.mvi.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S,A>(val view:BaseView,State:S) :LifecycleOwner{
    private val viewState: MutableStateFlow<S> = MutableStateFlow(State)
    private val vState:StateFlow<S> = viewState.asStateFlow()
    val _state:S
    get() = vState.value
//    lateinit var view:BaseView
    abstract fun onAction(action:A)
    fun setState(reduce:S.()->S){
        viewState.value=_state.reduce()
    }
    @Composable
    fun bind(): State<S> {
        return vState.collectAsState()
    }

    override fun getLifecycle(): Lifecycle {
        return view.getLifeCycle()
    }
}


