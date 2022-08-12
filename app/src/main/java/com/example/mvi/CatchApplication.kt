package com.example.mvi

import com.drake.net.NetConfig
import com.drake.net.convert.NetConverter
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setSSLCertificate
import com.koader.arch.CrashApplication
import com.koader.arch.utils.GlobalConfig
import com.koader.arch.utils.GsonConvert
import com.koader.arch.utils.HttpUtils
import retrofit2.converter.gson.GsonConverterFactory

class CatchApplication : CrashApplication() {
    override fun onCreate() {
        super.onCreate()
        NetConfig.initialize("https://192.168.1.119:8000"){
            setSSLCertificate(resources.openRawResource(R.raw.media))
            setConverter(GsonConvert())
        }
//        HttpUtils.init(applicationContext,
//            GlobalConfig.BaseConfig(url = "https://192.168.1.119:8000",
//                domain = "192.168.1.119",
//                pub_key = "MIICJzCCAc2gAwIBAgIQAOaCcCwhdaRnNGkn56WOazAKBggqhkjOPQQDAjAYMRYw\n" +
//                        "FAYDVQQDDA0xOTIuMTY4LjEuMTE5MB4XDTIyMDcyOTA2NTYyMFoXDTIzMDcyOTA2\n" +
//                        "NTYyMFowGDEWMBQGA1UEAwwNMTkyLjE2OC4xLjExOTBZMBMGByqGSM49AgEGCCqG\n" +
//                        "SM49AwEHA0IABMLr/+jf9CheUbkGej8CnZFFKFtGBjgrVWQ/QRhluMr1A+bKQx8L\n" +
//                        "hSz5AuSu30IsUTy+4bVii4dYMI91zVnTVvyjgfgwgfUwVQYDVR0RBE4wTIIMbS5r\n" +
//                        "b2FkZXIudG9wgg53d3cua29hZGVyLnRvcIIQY2xvdWQua29hZGVyLnRvcIIObmFz\n" +
//                        "LmtvYWRlci50b3CHBH8AAAGHBMCoAXcwHQYDVR0OBBYEFO9c/uRaMeDWqI3/KvUO\n" +
//                        "dghTrKPPMA4GA1UdDwEB/wQEAwIBhjAPBgNVHRMBAf8EBTADAQH/MDsGA1UdJQQ0\n" +
//                        "MDIGCCsGAQUFBwMCBggrBgEFBQcDAQYIKwYBBQUHAwMGCCsGAQUFBwMEBggrBgEF\n" +
//                        "BQcDCDAfBgNVHSMEGDAWgBTvXP7kWjHg1qiN/yr1DnYIU6yjzzAKBggqhkjOPQQD\n" +
//                        "AgNIADBFAiAAjPaPgX1spk0Aizn8k/ZWABGLu97UArZe7MlJgrqBqAIhAPHUoqJx\n" +
//                        "tOWd9EwKI0LvBT4rhp3MQSPc4Cz1d/iv2A7F")
//            ,true,R.raw.media)
    }
}