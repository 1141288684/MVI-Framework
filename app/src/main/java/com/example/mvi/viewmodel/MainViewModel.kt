package com.example.mvi.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.drake.net.Get
import com.drake.net.NetConfig
import com.drake.net.utils.scopeNetLife
import com.koader.arch.base.BaseView
import com.koader.arch.base.BaseViewModel
import com.example.mvi.service.TestService
import com.google.gson.Gson
import com.koader.arch.utils.HttpUtils
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

//class MainViewModel:ViewModel() {
//
//    private val _viewState: MutableStateFlow<MainViewState> = MutableStateFlow(MainViewState())
//    val viewState = _viewState.asStateFlow()
////    private val _viewEvent=Channel<MainViewEvent>(Channel.BUFFERED)
////    val viewEvent = _viewEvent.receiveAsFlow()
//
//    private fun state():MainViewState{
//        return _viewState.value
//    }
//
//    private fun setState(state: MainViewState){
//        _viewState.value=state
//    }
//
//    suspend fun onAction(action:MainActivity.MainAction){
//
//        when(action){
//            MainActivity.MainAction.Add->{
//                coroutineScope {
//                    setState(state().copy(int = _viewState.value.int+1))
//                }
//            }
//        }
//    }
//
//}
//data class CounterState(val count: Int = 0) : MavericksState
//class CounterViewModel(initialState: CounterState) : MavericksViewModel<CounterState>(initialState) {
//    fun incrementCount() = setState { copy(count = count + 1) }
//}
class MainViewModel(view:BaseView) : BaseViewModel<MainViewState, MainAction>(view, MainViewState()){
    override fun onAction(action: MainAction) {
        when(action){
            MainAction.Add->{
                _state.list?.add(1)
                setState { copy(list = list) }
            }
            MainAction.Http->{
                scopeNetLife {
                    val map=Get<User>("").await()
                    setState { copy(text = map.name) }
                }
//                lifecycleScope.launch {
//                    val map= HttpUtils.getService(TestService::class.java).test()
//                    setState { copy(text=map.name) }
//                }
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
    val int: Int=0,
    val text:String="",
    val list: MutableList<Int>?= mutableStateListOf()
)
