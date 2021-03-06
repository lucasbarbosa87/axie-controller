package br.com.mercury.axieinfinityapi.utils

import android.content.Context
import com.facebook.stetho.Stetho
import com.google.gson.Gson
import com.google.gson.GsonBuilder

inline fun <reified T> jsonToObject(json: String, withExpose: Boolean = false): T {
    val gson = if (withExpose) {
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create()
    } else {
        Gson()
    }
    return gson.fromJson(json, T::class.java)
}

inline fun <reified T> objectToJson(objectType: T): String {
    return Gson().toJson(objectType)
}

fun initializeStetho(context: Context) {
    Stetho.initializeWithDefaults(context)
}