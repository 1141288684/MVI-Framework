package com.example.mvi.base

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle


abstract class BaseActivity<S,A> :ComponentActivity(),BaseView{
    private lateinit var viewModel: BaseViewModel<S, A>
    abstract fun setViewModel():BaseViewModel<S,A>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel=setViewModel()
            val state by viewModel.bind()
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Build(state)
            }
        }
    }

    @Composable
    abstract fun Build(state: S)

    fun doAction(action:A){
        viewModel.onAction(action)
    }


    override fun getLifeCycle(): Lifecycle {
        return lifecycle
    }

    override fun getContext(): Context {
        return baseContext
    }
}