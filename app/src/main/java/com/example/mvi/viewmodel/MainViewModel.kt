package com.example.mvi.viewmodel

import androidx.lifecycle.viewModelScope
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.example.mvi.activity.MainActivity
import com.koader.arch.base.*
import com.kunminx.architecture.domain.dispatch.MviDispatcherKTX
import kotlinx.coroutines.launch


class MainViewModel(view:BaseView) : BaseViewModel<MainViewModel.MainViewState, MainViewModel.MainAction>(view, MainViewState()){
    var tmp:Int=0

    override fun onAction(action: MainAction) {

        when(action){
            MainAction.Add->{
                setState {
//                    val newlist=list
//                    newlist.add(tmp++.toString())
//                    copy(list = newlist)
                    it.int+="1"
                    it.list.add(tmp++.toString())
                }
//                setState { it.list.add("1") }
            }
            MainAction.Http->{
                scopeNetLife {
                    val map=Get<User>("").await()
//                    setState { copy(text = map.name) }
                    setState { it.text=map.name }
                }
            }
            else -> {
                if (action is MainAction.Selected){
//                    setState { copy(text = action.index.toString()) }
                    setState { it.text=action.index.toString() }
                }
            }
        }
    }
    sealed class MainAction{
        object Add:MainAction()
        object Http: MainAction()
        data class Selected(val index:Int):MainAction()
    }
    class MainViewState (
        var hasNext: Boolean = true,
        var int: String = "",
        var text: String = "",
        val list: MutableList<String> = mutableListOf()
    ):BaseState()
}
