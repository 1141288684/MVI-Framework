package com.example.mvi.viewmodel

import androidx.compose.runtime.MutableState
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.koader.arch.base.*


class MainViewModel(view:BaseView) : BaseViewModel<MainViewModel.MainViewState, MainViewModel.MainAction>(view, MainViewState()){
    override fun onAction(action: MainAction) {
        when(action){
            MainAction.Add->{
                state.int.value+="1"
//                _state.list?.add("1")
//                setState { copy(list = list) }
            }
            MainAction.Http->{
                scopeNetLife {
                    val map=Get<User>("").await()
//                    setState { copy(text = map.name) }
                }
//                lifecycleScope.launch {
//                    val map= HttpUtils.getService(TestService::class.java).test()
//                    setState { copy(text=map.name) }
//                }
            }
        }
    }
    enum class MainAction{
        Add,
        Http
    }
    data class MainViewState(
        val isLoading:Boolean=false,
        val int: MutableState<String> = initVar(""),
        val text:String="",
        val list: MutableStateList<String> = initList()
    )
}

