package com.koader.arch.utils

import com.drake.net.NetConfig
import com.drake.net.convert.JSONConvert
import com.drake.net.exception.ConvertException
import com.drake.net.exception.RequestParamsException
import com.drake.net.exception.ResponseException
import com.drake.net.exception.ServerResponseException
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Type

//object GsonConvert : JSONConvert(code = "code", message = "message", success = "200") {
//    private val gson: Gson = GsonBuilder().serializeNulls().create()
//
//    override fun <S> String.parseBody(succeed: Type): S? {
//        return gson.fromJson(this, succeed)
//    }
//}
val GsonConvert=GsonConverter(data = "data", code = "code", message = "message", success = "200")
/**
 * data——真正数据存储所在的字段，默认为根
 */
class GsonConverter(
    private val data:String="",
    code:String="code",
    message:String="msg",
    success:String="0") : JSONConvert(code = code, message = message, success = success){
    lateinit var pData:String
    private val gson: Gson = GsonBuilder().serializeNulls().create()
    override fun <R> String.parseBody(succeed: Type): R? {
        return if(pData.isEmpty()){
            gson.fromJson(this,succeed)
        }else{
            gson.fromJson(pData,succeed)
        }
    }

    override fun <R> onConvert(succeed: Type, response: Response): R? {
        val code = response.code
        when {
            code in 200..299 -> { // 请求成功
                val bodyString = response.body?.string() ?: return null
                return try {
                    val json = JSONObject(bodyString) // 获取JSON中后端定义的错误码和错误信息
                    val srvCode = json.getString(this.code)
                    pData = if (data.isEmpty())"" else json.getString(this.data)
                    if (srvCode == success) { // 对比后端自定义错误码
                        bodyString.parseBody<R>(succeed)
                    } else { // 错误码匹配失败, 开始写入错误异常
                        val errorMessage = json.optString(message, NetConfig.app.getString(com.drake.net.R.string.no_error_message))
                        throw ResponseException(response, errorMessage, tag = srvCode) // 将业务错误码作为tag传递
                    }
                } catch (e: JSONException) { // 固定格式JSON分析失败
                    throw ConvertException(response)
                }
            }
            code in 400..499 -> throw RequestParamsException(response, code.toString()) // 请求参数错误
            code >= 500 -> throw ServerResponseException(response, code.toString()) // 服务器异常错误
            else -> throw ConvertException(response)
        }

    }
}