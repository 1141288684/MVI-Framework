package com.example.mvi.di.component

import com.example.mvi.di.module.MainModule
import com.koader.arch.base.BaseView
import dagger.Component
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
@DefineComponent(parent = SingletonComponent::class)
interface MainComponent