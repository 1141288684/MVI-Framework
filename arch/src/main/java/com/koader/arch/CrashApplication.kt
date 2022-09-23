package com.koader.arch

import android.app.Application
import com.koader.arch.utils.GlobalConfig
import com.koader.arch.utils.HttpUtils
import com.tencent.mmkv.MMKV

open class CrashApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        CrashHandler().init(applicationContext)
        MMKV.initialize(this)
    }
}