package com.ysk.beeonetask.utils

import android.content.Context

object Constants {
    val BASE_URL="https://api.openweathermap.org/data/2.5/"


    fun Context.getvaluefromsp(context: Context, key: String): String {
        val sp = context.getSharedPreferences("Base", 0)
        return sp.getString(key, "")!!
    }

    fun Context.putvaluetosp(context: Context, key: String, data: String) {
        val sp = context.getSharedPreferences("Base", 0).edit()
        sp.putString(key, data).apply()
    }

    fun Context.clearSession(context: Context) {
        val sp = context.getSharedPreferences("Base", 0).edit()
        sp.clear()
        sp.apply()
    }
}