package com.example.mvi.di

import com.example.mvi.activity.SecondViewModel
import com.example.mvi.viewmodel.MainViewModel
import com.example.mvi.viewmodel.MainViewModel.MainViewState
import com.example.mvi.viewmodel.User
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val MainModule = module{
    single { androidContext() }
    single { MainViewModel(get()) }
    single { User("assdadsa") }
}

val SecondModule = module {
    single { androidContext() }

    single { SecondViewModel(get()) }
}