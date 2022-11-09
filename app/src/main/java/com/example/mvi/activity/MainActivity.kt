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
import com.koader.jrouter.JRouter
import org.koin.android.ext.koin.androidContext

class MainActivity : BaseActivity<MainViewState, MainAction>(){

    private val user by inject<User>()
    override fun setViewModel(): BaseViewModel<MainViewState, MainAction> {
        startKoin {
            modules(MainModule)
            androidContext(this@MainActivity)
            addWithContext<BaseView>(this@MainActivity)
        }
        return inject<MainViewModel>().value
    }

    @Composable
    override fun Build(state:MainViewState) {
        requestPermissions(arrayOf(Manifest.permission.INTERNET),1)
        Column {
            Text(text = user.name)
            Text(text = state.int)

            LazyColumn(content = {
                items(state.list){
                    Button(onClick = { doAction(MainAction.Selected(state.list.indexOf(it))) }) {
                        Text(text = it)
                    }
                }
                item { Text(text = if (state.hasNext) "加载更多" else "没有更多数据") }
            })
            Button(onClick = { doAction(MainAction.Add) }) {
                Text(text = "加一")
            }
            Button(onClick = { doAction(MainAction.Http) }) {
                Text(text = "测试框架及https通信")
            }
//            Text(text = state.text)
            Button(onClick = { JRouter.with("id","测试").startActivity("route/second") }) {
                Text(text = "测试全局路由-fragment")
            }
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