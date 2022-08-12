package com.koader.arch.utils

import com.drake.net.convert.JSONConvert
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

class GsonConvert : JSONConvert(code = "code", message = "msg", success = "200") {
    private val gson: Gson = GsonBuilder().serializeNulls().create()

    override fun <S> String.parseBody(succeed: Type): S? {
        return gson.fromJson(this, succeed)
    }
}