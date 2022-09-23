package com.example.mvi.activity

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvi.di.MainModule

import com.example.mvi.ui.theme.MVITheme
import com.example.mvi.viewmodel.*
import com.koader.arch.base.*
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin
import com.example.mvi.viewmodel.MainViewModel.*

//@AndroidEntryPoint
//class MainActivity : BaseActivityMV<MineViewModel>(){
//
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////
////        setContent {
////            Surface(modifier = Modifier.fillMaxSize()) {
////                Column {
////                    Build()
////                }
////            }
////        }
////    }
//
//    @Composable
//    override fun Build() {
//        var v by Var("")
//        val l = mutableStateListOf<String>()
//        Column {
////            Text(text = v?:"")
////            Button(onClick = { v += 1 }) {
////                Text(text = "测试")
////            }
//            Button(onClick = { l.add("1") }) {
//                Text(text = "测试")
//            }
//            LazyColumn{
//                items(l){
//                    Text(text = it)
//                }
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
            Text(text = state.int)

            LazyColumn(content = {
                items(state.list){
                    Text(text = it)
                }
                item { Text(text = if (state.hasNext) "加载更多" else "没有更多数据") }
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