package com.example.mvi.viewmodel

import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.koader.arch.base.*


class MainViewModel(view:BaseView) : BaseViewModel<MainViewModel.MainViewState, MainViewModel.MainAction>(view, MainViewState()){
    override fun onAction(action: MainAction) {
        when(action){
            MainAction.Add->{
                setState {
                    it.int+="1"
                    it.list.add("1")
                }
//                setState { it.list.add("1") }
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
    class MainViewState:BaseState() {
        val isLoading: Boolean = false
        var int: String = ""
        val text: String = ""
        val list: MutableList<String> = mutableListOf()
    }
}

