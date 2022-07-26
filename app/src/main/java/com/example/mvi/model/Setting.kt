package com.example.mvi.model

class Setting (data:Any,vararg arguments:Any){
    val data:Any
    val arguments:Array<out Any>
    init {
        this.data=data
        this.arguments=arguments
    }
}