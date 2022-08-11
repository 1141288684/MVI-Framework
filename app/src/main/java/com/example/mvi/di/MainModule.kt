package com.example.mvi.di

import com.example.mvi.viewmodel.MainViewModel
import com.example.mvi.viewmodel.MainViewState
import com.example.mvi.viewmodel.User
import org.koin.dsl.module

val MainModule = module{
    single { MainViewState() }
    single { MainViewModel(get()) }
    single { User("assdadsa") }
}