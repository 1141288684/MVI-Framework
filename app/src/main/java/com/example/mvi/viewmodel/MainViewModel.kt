package com.example.mvi.viewmodel

import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.koader.arch.base.*


class MainViewModel(view:BaseView) : BaseViewModel<MainViewModel.MainViewState, MainViewModel.MainAction>(view, MainViewState()){
    override fun onAction(action: MainAction) {
        when(action){
            MainAction.Add->{
                setState { copy(list = list.apply { add("1") }) }

            }
            MainAction.Http->{
                scopeNetLife {
                    val map=Get<User>("").await()
                }
            }
        }
    }
    enum class MainAction{
        Add,
        Http
    }
    data class MainViewState(
        val isLoading:Boolean=false,
        val int: String="",
        val text:String="",
        val list: MutableList<String> = initList()
    )
}

