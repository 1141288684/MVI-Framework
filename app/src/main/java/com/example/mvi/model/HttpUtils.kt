package com.example.mvi.model

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.util.*
import kotlin.collections.HashMap

class HttpUtils private constructor(private val retrofit: Retrofit) {
    private val services:HashMap<String,Any> = HashMap()

    companion object{
        private var ins:HttpUtils?=null

        private fun getInstance(context: Context):HttpUtils{
            if(ins==null){
                val builder=OkHttpClient.Builder()
                MyTrustManager.onHttpCertificate(context,builder)
                ins = HttpUtils(Retrofit.Builder().client(builder.build()).baseUrl(GlobalConfig.url)
                    .addConverterFactory(GsonConverterFactory.create()).build())
            }
            return ins!!
        }

        fun <T:Any>getService(context: Context,service:Class<T>):T{
            val i = getInstance(context)
            if (!i.services.containsKey(service.name)){
                i.services[service.name]=i.retrofit.create(service)
            }
            return i.services[service.name] as T
        }
    }
}