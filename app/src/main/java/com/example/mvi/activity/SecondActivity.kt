package com.example.mvi.activity

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.koader.arch.base.*
import com.koader.jrouter.JRouter.set
import com.koader.jrouter.Just

class SecondActivity: BaseActivity<SecondState, SecondViewModel.SecondAction>() {

    @Just("id")
    val id by set<String>("id")

    override fun setViewModel(): BaseViewModel<SecondState, SecondViewModel.SecondAction> {
//        JRouter.inject(this)
//        startKoin {
//            modules(SecondModule)
//            androidContext(this@SecondActivity)
//            addWithContext<BaseView>(this@SecondActivity)
//        }
        return SecondViewModel(this)
    }

    @Composable
    override fun Build(state: SecondState) {
        Text(text = "打开新页面$id")
    }
}

class SecondState:BaseState(){
    var test:String=""
}
class SecondViewModel(view: BaseView): BaseViewModel<SecondState, SecondViewModel.SecondAction>(view,
    SecondState()
) {
    sealed class SecondAction:BaseAction{

    }

    override fun onAction(action: SecondAction) {
        TODO("Not yet implemented")
    }
}