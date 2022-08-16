package com.koader.arch.utils

import android.content.Context
import com.google.gson.Gson
import java.io.InputStreamReader

class GlobalConfig private constructor(){
    companion object{


        private val gson = Gson()


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
        val pub_key:String?=""
    )
}
