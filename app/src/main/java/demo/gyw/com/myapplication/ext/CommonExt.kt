package demo.gyw.com.myapplication.ext

import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

val now = {
    dateFormat.format(Date(System.currentTimeMillis()))
}

fun log(msg: Any?) = Log.d("goyw", "${now()} [${Thread.currentThread().name}] $msg")



fun Float.dp2px(context: Context): Float{
    val scale = context.resources.displayMetrics.density
    return this * scale
}


fun Float.px2dp(context: Context): Float{
    val scale = context.resources.displayMetrics.density
    return this / scale
}

