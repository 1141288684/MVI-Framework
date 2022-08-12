package com.koader.arch.base

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

abstract class BaseViewModelMV(private val view: BaseView) :LifecycleOwner{
    override fun getLifecycle(): Lifecycle {
        return view.getLifeCycle()
    }

//    @Composable
//    abstract fun createState(content:@Composable ()->Unit)
}