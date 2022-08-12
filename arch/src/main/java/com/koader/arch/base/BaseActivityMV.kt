package com.koader.arch.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

abstract class BaseActivityMV<T:BaseViewModelMV> :ComponentActivity(),BaseView{
    lateinit var viewModel:T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel=setViewModel()
//            val state by viewModel.bind()
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Build()
            }

        }
    }

    @Composable
    abstract fun Build()

    abstract fun setViewModel():T
}