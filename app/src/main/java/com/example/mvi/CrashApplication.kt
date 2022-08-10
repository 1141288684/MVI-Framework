package com.example.mvi

import android.app.Application
import com.koader.arch.utils.GlobalConfig
import com.koader.arch.utils.HttpUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CrashApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        CrashHandler().init(applicationContext)
        GlobalConfig.instance.getConfig(R.raw.config,GlobalConfig.BaseConfig::class.java,applicationContext)
        HttpUtils.init(applicationContext,true,R.raw.media)
    }
}