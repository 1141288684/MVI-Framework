package com.example.mvi.viewmodel

import com.example.mvi.base.BaseViewModel

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
class MainViewModel : BaseViewModel<MainViewState, MainAction>(MainViewState()){
    override fun onAction(action: MainAction) {
        when(action){
            MainAction.Add->{
                setState(state.copy(int = state.int+1))
            }
        }
    }

}
enum class MainAction{
    Add,
}
data class MainViewState(
    val isLoading:Boolean=false,
    val int: Int=0

)
