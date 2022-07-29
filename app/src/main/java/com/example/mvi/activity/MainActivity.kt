package com.example.mvi.activity

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.mvi.base.BaseActivity
import com.example.mvi.base.BaseViewModel
import com.example.mvi.ui.theme.MVITheme
import com.example.mvi.viewmodel.*

class MainActivity : BaseActivity<MainViewState, MainAction>() {

    override fun setViewModel(): BaseViewModel<MainViewState, MainAction> {
        return MainViewModel(this)
    }

    @Composable
    override fun Build(state:MainViewState) {
        requestPermissions(arrayOf(Manifest.permission.INTERNET),1)
        Column {
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