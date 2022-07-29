package com.example.mvi.base

import android.content.Context
import androidx.lifecycle.Lifecycle

interface BaseView {
    fun getContext():Context

    fun getLifeCycle():Lifecycle
}