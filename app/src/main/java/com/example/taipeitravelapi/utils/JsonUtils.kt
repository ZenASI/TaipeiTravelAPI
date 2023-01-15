package com.example.taipeitravelapi.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okio.buffer
import okio.source
import java.io.ByteArrayInputStream
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class JsonUtils @Inject constructor(
    val gson: Gson
) {

    fun <T> gsonFromJson(data: String, type: Class<T>): T? {
        return try {
            gson.fromJson<T>(data, type)
        } catch (e: Exception) {
            Log.d("JsonUtils", "gsonFromJson: ${e.printStackTrace()}")
            null
        }
    }

    // https://stackoverflow.com/questions/57818332/class-com-google-gson-internal-linkedtreemap-cannot-be-cast-to-class-partner
    inline fun <reified T> gsonFromJson(data: String): List<T> {
        return try {
            val type = object : TypeToken<List<T>>() {}.type
            gson.fromJson<List<T>>(data, type)
        } catch (e: Exception) {
            Log.d("JsonUtils", "gsonFromJson: ${e.printStackTrace()}")
            emptyList()
        }
    }

    fun <T> gson2Json(data: T): String {
        return try {
            gson.toJson(data)
        } catch (e: Exception) {
            Log.d("JsonUtils", "gson2Json: ${e.message}")
            ""
        }
    }
}