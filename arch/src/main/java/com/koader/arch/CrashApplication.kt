package com.koader.arch

import android.app.Application
import com.koader.arch.utils.GlobalConfig
import com.koader.arch.utils.HttpUtils

open class CrashApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        CrashHandler().init(applicationContext)
    }
}