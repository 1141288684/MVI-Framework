package com.example.mvi.di.module

import com.example.mvi.di.component.MainComponent
import com.example.mvi.viewmodel.MainViewModel
import com.example.mvi.viewmodel.User
import com.koader.arch.base.BaseView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn


@Module
@InstallIn(MainComponent::class)
class MainModule {

    @Provides
    fun provideMainViewModel(view:BaseView):MainViewModel{
        return MainViewModel(view)
    }

    @Provides
    fun user():User{
        return User("sadasd")
    }
}