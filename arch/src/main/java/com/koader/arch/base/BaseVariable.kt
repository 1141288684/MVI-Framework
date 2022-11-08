package com.koader.arch.base

import android.app.Application
import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.dsl.binds
import org.koin.dsl.module

typealias MutableStateList<T> = SnapshotStateList<T>
typealias MutableStateMap<K,V> = SnapshotStateMap<K, V>

fun <T>initVar(v:T):MutableState<T>{
    return mutableStateOf(v)
}

fun <T>initList():MutableList<T>{
    return mutableStateListOf()
}
fun <T>initList(vararg elements:T):MutableList<T>{
    return mutableStateListOf(*elements)
}
fun <K,V>initMap():MutableMap<K,V>{
    return mutableStateMapOf()
}
fun <K,V>initMap(vararg pairs:Pair<K,V>):MutableMap<K,V>{
    return mutableStateMapOf(*pairs)
}
fun KoinApplication.androidView(androidView: BaseView): KoinApplication {
    if (koin.logger.isAt(Level.INFO)) {
        koin.logger.info("[init] declare Android Context")
    }

    koin.loadModules(listOf(module {
        single { androidView } binds arrayOf(Context::class, BaseView::class)
    }))

    return this
}

inline fun <reified T> KoinApplication.addWithContext(e:T):KoinApplication{
    if (koin.logger.isAt(Level.INFO)) {
        koin.logger.info("[init] declare Android Context")
    }

    koin.loadModules(listOf(module {
        single { e } binds arrayOf(Context::class, T::class)
    }))

    return this
}