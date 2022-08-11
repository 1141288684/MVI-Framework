package com.koader.arch.utils

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.io.InputStream
import kotlin.collections.HashMap

class HttpUtils private constructor(private val retrofit: Retrofit) {
    private val services:HashMap<String,Any> = HashMap()

    companion object{
        private lateinit var ins: HttpUtils

        /**
         * 使用前必须先在GlobalConfig获取BaseConfig配置
         * cert-证书资源Id
         */
        fun init(context: Context,config: GlobalConfig.BaseConfig,https:Boolean=false,cert: Int?=null){
            if(!::ins.isInitialized){
                val builder=OkHttpClient.Builder()
                if (https)MyTrustManager.onHttpCertificate(context.resources.openRawResource(cert!!), builder,config)
                ins = HttpUtils(Retrofit.Builder().client(builder.build()).baseUrl(
                    config.url)
                    .addConverterFactory(GsonConverterFactory.create()).build())
            }
        }

        @Suppress("UNCHECKED_CAST")
        fun <T:Any>getService(service:Class<T>):T{
            if (!ins.services.containsKey(service.name)){
                ins.services[service.name]=ins.retrofit.create(service)
            }
            return ins.services[service.name] as T
        }
    }

    interface Certificate{
        fun getCertificates(resId:Int):InputStream
    }
}