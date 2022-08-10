package com.koader.arch.utils

import android.content.Context
import com.google.gson.Gson
import java.io.InputStreamReader

class GlobalConfig private constructor(){
    companion object{
//        private lateinit var globalConfig:GlobalConfigImpl
//        const val domain:String="192.168.1.119"
//        const val url:String="https://${domain}:8000"
//        val domain:String
//        get() = globalConfig.getDomain()
//        val url:String
//        get() = globalConfig.getUrl()
//        fun setGlobalConfig(globalConfigImpl: GlobalConfigImpl){
//            globalConfig=globalConfigImpl
//        }

        private val gson = Gson()

        val instance = GlobalConfig()

        private val map = HashMap<String,Any>()

    }
    @Suppress("UNCHECKED_CAST")
    fun <T : Any>getConfig(json:String, clazz: Class<T>):T{
        if (!map.containsKey(clazz.typeName)){
            map[clazz.typeName] = gson.fromJson(json,clazz)
        }
        return map[clazz.typeName] as T
    }
    @Suppress("UNCHECKED_CAST")
    fun <T : Any>getConfig(resId:Int,clazz:Class<T>,context: Context):T{
        if (!map.containsKey(clazz.typeName)){
            map[clazz.typeName] = gson.fromJson(InputStreamReader(context.resources.openRawResource(resId)),clazz)
        }
        return map[clazz.typeName] as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any>getConfig(clazz: Class<T>):T?{
        if (!map.containsKey(clazz.typeName)){
            return null
        }
        return map[clazz.typeName] as T
    }

    fun <T:Any>putConfig(obj:T){
        map[obj::class.java.typeName]=obj
    }

    data class BaseConfig(
        val url:String,
        val domain:String,
        val pub_key:String
    )
}
