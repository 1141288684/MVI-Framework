package com.koader.arch.base

abstract class BaseState {
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}