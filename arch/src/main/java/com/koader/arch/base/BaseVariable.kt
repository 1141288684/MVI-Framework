package com.koader.arch.base

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap

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
