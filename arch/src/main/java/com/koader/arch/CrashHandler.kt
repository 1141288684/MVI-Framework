package com.koader.arch

import android.content.Context
import android.widget.Toast
import java.text.DateFormat

class CrashHandler:Thread.UncaughtExceptionHandler{
    private val tag = "CrashHandler"

    //系统默认的UncaughtException处理类
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null

    //程序的Context对象
    private var mContext: Context? = null

    //用来存储设备信息和异常信息
    private val infos: HashMap<String, String> = HashMap()

    //用于格式化日期,作为日志文件名的一部分
    private val formatter: DateFormat = DateFormat.getDateTimeInstance()




    /**
     * 初始化
     *
     * @param context
     */
    fun init(context: Context?) {
        mContext = context
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    override fun uncaughtException(thread: Thread, ex: Throwable) {
        ex.printStackTrace()
        Toast.makeText(mContext,ex.message, Toast.LENGTH_LONG).show()
//        if (!handleException(ex) && mDefaultHandler != null) {
//            //如果用户没有处理则让系统默认的异常处理器来处理
//            mDefaultHandler!!.uncaughtException(thread, ex)
//        } else {
//            try {
//                Thread.sleep(3000)
//            } catch (e: InterruptedException) {
//                Log.e(tag, "error : ", e)
//            }
//            //退出程序
//            Process.killProcess(Process.myPid())
//            System.exit(1)
//        }
    }

//    /**
//     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
//     *
//     * @param ex
//     * @return true:如果处理了该异常信息;否则返回false.
//     */
//    private fun handleException(ex: Throwable?): Boolean {
//        if (ex == null) {
//            return false
//        }
//        //使用Toast来显示异常信息
//        object : Thread() {
//            override fun run() {
//                Looper.prepare()
//                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show()
//                Looper.loop()
//            }
//        }.start()
//        //收集设备参数信息
//        collectDeviceInfo(mContext!!)
//        //保存日志文件
//        saveCrashInfo2File(ex)
//        return true
//    }
//
//    /**
//     * 收集设备参数信息
//     * @param ctx
//     */
//    fun collectDeviceInfo(ctx: Context) {
//        try {
//            val pm: PackageManager = ctx.packageManager
//            val pi: PackageInfo =
//                pm.getPackageInfo(ctx.packageName, PackageManager.GET_ACTIVITIES)
//            val versionName = if (pi.versionName == null) "null" else pi.versionName
//            val versionCode = pi.versionCode.toString() + ""
//            infos["versionName"] = versionName
//            infos["versionCode"] = versionCode
//        } catch (e: PackageManager.NameNotFoundException) {
//            Log.e(tag, "an error occured when collect package info", e)
//        }
//        val fields: Array<Field> = Build::class.java.declaredFields
//        for (field in fields) {
//            try {
//                field.isAccessible = true
//                infos[field.name] = field.get(null)?.toString()!!
//                Log.d(tag, field.getName().toString() + " : " + field.get(null))
//            } catch (e: Exception) {
//                Log.e(tag, "an error occured when collect crash info", e)
//            }
//        }
//    }
//
//    /**
//     * 保存错误信息到文件中
//     *
//     * @param ex
//     * @return  返回文件名称,便于将文件传送到服务器
//     */
//    private fun saveCrashInfo2File(ex: Throwable): String? {
//        val sb = StringBuffer()
////        for ((key, value): Map.Entry<String, String> in infos) {
////            sb.append("$key=$value\n")
////        }
//        val writer: Writer = StringWriter()
//        val printWriter = PrintWriter(writer)
//        ex.printStackTrace(printWriter)
//        var cause = ex.cause
//        while (cause != null) {
//            cause.printStackTrace(printWriter)
//            cause = cause.cause
//        }
//        printWriter.close()
//        val result: String = writer.toString()
//        sb.append(result)
//        try {
//            val timestamp = System.currentTimeMillis()
//            val time: String = formatter.format(Date())
//            val fileName = "crash-$time-$timestamp.log"
//            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
//                val path = Environment.getExternalStorageDirectory().path
//                val dir = File(path)
//                if (!dir.exists()) {
//                    dir.mkdirs()
//                }
//                val fos = FileOutputStream(path + fileName)
//                fos.write(sb.toString().toByteArray())
//                fos.close()
//            }
//            return fileName
//        } catch (e: Exception) {
//            Log.e(tag, "an error occured while writing file...", e)
//        }
//        return null
//    }
}