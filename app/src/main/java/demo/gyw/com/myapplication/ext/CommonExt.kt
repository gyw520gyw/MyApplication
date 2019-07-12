package demo.gyw.com.myapplication.ext

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

val now = {
    dateFormat.format(Date(System.currentTimeMillis()))
}

fun log(msg: Any?) = Log.d("goyw", "${now()} [${Thread.currentThread().name}] $msg")

