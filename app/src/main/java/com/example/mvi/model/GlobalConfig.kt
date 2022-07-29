package com.example.mvi.model

import com.example.mvi.GlobalConfigImpl

class GlobalConfig{
    companion object{
        private lateinit var globalConfig:GlobalConfigImpl
//        const val domain:String="192.168.1.119"
//        const val url:String="https://${domain}:8000"
        val domain:String
        get() = globalConfig.getDomain()
        val url:String
        get() = globalConfig.getUrl()
        fun setGlobalConfig(globalConfigImpl: GlobalConfigImpl){
            globalConfig=globalConfigImpl
        }
    }
}
