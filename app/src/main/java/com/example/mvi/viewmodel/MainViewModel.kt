package com.example.mvi.viewmodel

import androidx.lifecycle.lifecycleScope
import com.koader.arch.base.BaseView
import com.koader.arch.base.BaseViewModel
import com.example.mvi.service.TestService
import com.koader.arch.utils.HttpUtils
import kotlinx.coroutines.launch

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
                setState{copy(int = _state.int+1)}
            }
            MainAction.Http->{
                lifecycleScope.launch {
                    val map= HttpUtils.getService(TestService::class.java).test()
                    setState { copy(text=map.name) }
                }
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
    val text:String=""
)
