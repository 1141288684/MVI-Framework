package com.example.mvi

import android.app.Application
import com.example.mvi.model.GlobalConfig

class CrashApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        CrashHandler().init(applicationContext)
        GlobalConfig.setGlobalConfig(object :GlobalConfigImpl{
            override fun getDomain(): String {
                return "192.168.1.119"
            }

            override fun getUrl(): String {
                return "https://192.168.1.119:8000"
            }
        })
    }
}