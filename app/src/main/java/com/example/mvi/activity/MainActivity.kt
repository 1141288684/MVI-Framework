package com.example.mvi.activity

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvi.di.MainModule

import com.example.mvi.ui.theme.MVITheme
import com.example.mvi.viewmodel.*
import com.koader.arch.base.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin

class MineViewModel(view:BaseView):BaseViewModelMV(view){
    var viewState: MutableLiveData<String> = MutableLiveData()

//    @Composable
//    override fun createState(content: () -> Unit) {
//        name = remember {
//            mutableStateOf("")
//        }
//    }

}
//@AndroidEntryPoint
//class MainActivity : BaseActivityMV<MineViewModel>(){
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            Surface(modifier = Modifier.fillMaxSize()) {
//                Column {
//
//                }
//            }
//        }
//    }
//
//    @Composable
//    override fun Build() {
//        viewModel.viewState.observe(this,this)
//        Column {
//            Text(text = v)
//            Button(onClick = { viewModel.viewState.value=viewModel.viewState.value+1}) {
//                Text(text = "测试")
//            }
//        }
//    }
//
//    override fun setViewModel(): MineViewModel {
//        return MineViewModel(this)
//    }
//
//    override fun getContext(): Context {
//        return this
//    }
//
//    override fun getLifeCycle(): Lifecycle {
//        return lifecycle
//    }
//}

class MainActivity : BaseActivity<MainViewState, MainAction>(){

    private val user by inject<User>()
    override fun setViewModel(): BaseViewModel<MainViewState, MainAction> {
        startKoin {
            modules(MainModule)
        }
        return MainViewModel(this)
    }

    @Composable
    override fun Build(state:MainViewState) {
        requestPermissions(arrayOf(Manifest.permission.INTERNET),1)
        Column {
            Text(text = user.name)
            Text(text = state.int.toString())
            LazyColumn(content = {
                items(state.int){
                    state.list!!.map { Text(text = it.toString()) }
                }
            })
            Button(onClick = { doAction(MainAction.Add) }) {
                Text(text = "加一")
            }
            Button(onClick = { doAction(MainAction.Http) }) {
                Text(text = "测试框架及https通信")
            }
            Text(text = state.text)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVITheme {
        Greeting("Android")
    }
}