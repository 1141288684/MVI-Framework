package com.example.mvi.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvi.base.BaseActivity
import com.example.mvi.ui.theme.MVITheme
import com.example.mvi.viewmodel.*

class MainActivity : BaseActivity<MainViewState, MainAction>(MainViewModel()) {
//    val viewModel = MainViewModel()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            val viewState by viewModel.viewState.collectAsState()
//
//            MVITheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Column() {
//                        Greeting("Android")
//                        Text(text = viewState.int.toString())
//                        Button(onClick = { doAction(MainAction.Add) }) {
//                            Text(text = "加一")
//                        }
//                    }
//
//                }
//            }
//        }
//    }

//    override fun doAction(action: MainAction){
//        lifecycleScope.launch {
//            viewModel.onAction(action)
//        }
//    }



    @Composable
    override fun Build(state:MainViewState) {
        Column {
            Text(text = state.int.toString())
            Button(onClick = { doAction(MainAction.Add) }) {
                Text(text = "加一")
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