package com.koader.arch.utils

class Setting (data:Any,vararg arguments:Any){
    val data:Any
    val arguments:Array<out Any>
    init {
        this.data=data
        this.arguments=arguments
    }
}