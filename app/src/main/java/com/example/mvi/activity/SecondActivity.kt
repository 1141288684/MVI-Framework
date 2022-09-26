package com.example.mvi.activity

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.koader.arch.base.BaseActivity
import com.koader.arch.base.BaseState
import com.koader.arch.base.BaseView
import com.koader.arch.base.BaseViewModel
import com.koader.jrouter.Just

class SecondActivity: BaseActivity<SecondState, SecondViewModel.SecondAction>() {
    override fun setViewModel(): BaseViewModel<SecondState, SecondViewModel.SecondAction> {
        return SecondViewModel(this)
    }

    @Composable
    override fun Build(state: SecondState) {
        Text(text = "打开新页面")
    }
}

class SecondState:BaseState(){
    var test:String=""
}
class SecondViewModel(view: BaseView): BaseViewModel<SecondState, SecondViewModel.SecondAction>(view,
    SecondState()
) {
    enum class SecondAction{

    }

    override fun onAction(action: SecondAction) {
        TODO("Not yet implemented")
    }
}