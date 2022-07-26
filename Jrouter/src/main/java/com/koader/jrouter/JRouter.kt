package com.koader.jrouter

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import java.text.NumberFormat.Field.INTEGER
import java.util.*
import java.util.concurrent.Executor
import javax.xml.xpath.XPathConstants.STRING
import kotlin.collections.HashMap
import kotlin.reflect.KClass

object JRouter {
    private lateinit var apps:Application
    private lateinit var routes: HashMap<String,KClass<*>>
//    private val builder:Builder= Builder()
    private val data:MutableMap<String,Any> = mutableMapOf()

    fun with(key:String,value:String):JRouter{
        data[key]=value
        return this
    }
    fun init(application: Application,vararg routes:Route){
        this.apps=application
        this.routes= HashMap()
        routes.forEach {
            this.routes[it.path]=it.route
        }
    }

    fun startActivity(path: String){
        apps.startActivity(Intent(apps.applicationContext,this.routes[path]?.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK })
    }

    fun bindService(path: String,serviceConnection: ServiceConnection,flags:Int){
        apps.bindService(Intent(apps.applicationContext,this.routes[path]?.java),serviceConnection,flags)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun bindService(path: String, flags:Int, executor:Executor, serviceConnection: ServiceConnection){
        apps.bindService(Intent(apps.applicationContext,this.routes[path]?.java)
            ,flags,executor,serviceConnection)
    }

    fun startService(path: String){
        apps.startService(Intent(apps.applicationContext,this.routes[path]?.java))
    }

    fun getFragment(path: String):Fragment{
        val f=this.routes[path]?.java?.newInstance() as Fragment
        this.routes[path]?.java?.declaredFields?.forEach {
            //设置f对象内注解name对应的注入值
            val n=it.getAnnotation(Just::class.java)?.name
            it.set(f,data[n])
            data.remove(n)
        }
        return f
    }

    fun inject(a:Any){
        a.javaClass.declaredFields.forEach {
            println(it.name)
            val n=it.getAnnotation(Just::class.java)?.name
            it.isAccessible=true
            it.set(a,data[n])
            data.remove(n)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T>set(name:String):Lazy<T>{
        return lazy { data[name] as T }
    }
}

interface RouteInterface{

}
data class Route(val path:String,val route:KClass<*>){
    enum class RouteType{
        ACTIVITY,
        SERVICE,
        FRAGMENT
    }
}
fun Route.getActivity(){

}