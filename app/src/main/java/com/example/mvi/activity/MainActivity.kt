package com.example.mvi.activity

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvi.di.MainModule

import com.koader.arch.base.BaseActivity
import com.koader.arch.base.BaseViewModel
import com.example.mvi.ui.theme.MVITheme
import com.example.mvi.viewmodel.*
import com.koader.arch.base.BaseView
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin


//@AndroidEntryPoint
//class MainActivity : ComponentActivity(){
//    @Inject
//    lateinit var user: User
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            Surface(modifier = Modifier.fillMaxSize()) {
//                Column() {
//                    Text(text = user.name)
//                }
//            }
//        }
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