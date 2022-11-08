package com.example.mvi.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import com.example.mvi.di.SecondModule
import com.koader.arch.base.*
import com.koader.jrouter.JRouter
import com.koader.jrouter.Just
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SecondActivity: BaseActivity<SecondState, SecondViewModel.SecondAction>() {

    @Just("id")
    lateinit var id:String

    override fun setViewModel(): BaseViewModel<SecondState, SecondViewModel.SecondAction> {
        JRouter.inject(this)
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
    enum class SecondAction{

    }

    override fun onAction(action: SecondAction) {
        TODO("Not yet implemented")
    }
}