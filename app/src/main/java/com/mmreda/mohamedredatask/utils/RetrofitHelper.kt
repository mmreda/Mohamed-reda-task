package com.mmreda.mohamedredatask.utils

import com.mmreda.mohamedredatask.R
import com.mmreda.mohamedredatask.TaskApplication
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

private fun responseErrorHandler(response: String, responseCode: Int): String {
    val context = TaskApplication.appContext.applicationContext
    return when {
        responseCode < 500 -> {
            try {
                val responseObject = JSONObject(response)

                if (responseObject.has("errors")) {
                    val error = responseObject.get("errors")
                    if (error is String)
                        return error
                    if (error is JSONObject) {
                        for (key: String in error.keys().iterator()) {
                            return error.getString(key)
                        }
                    }
                }
                responseObject.getString("message")
            } catch (e: Exception) {
                // Timber.e(e)
                if (null != e.message)
                    e.message!!
                else
                    context.getString(R.string.error)

            }
        }
        responseCode == 500 -> {
            context.getString(R.string.server_error)
        }
        else -> {
            context.getString(R.string.error)
        }
    }
}

private fun failureHandler(t: Throwable): String {
    val context = TaskApplication.appContext.applicationContext
    return if (t is IOException) {
        context.getString(R.string.no_internet)
    } else {
        context.getString(R.string.error)
    }
}

fun errorHandler(throwable: Throwable): String? {
    try {
        return if (throwable is HttpException) {
            val msg = responseErrorHandler(
                throwable.response()!!.errorBody()!!.string(),
                throwable.code()
            )
            msg.replace("[", "").replace("]", "")
        } else failureHandler(throwable)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun getErrorMessage(response: String): String {
    val context = TaskApplication.appContext.applicationContext
    try {
        val responseObject = JSONObject(response)

        if (responseObject.has("errors")) {
            val error = responseObject.get("errors")
            if (error is String)
                return error
            if (error is JSONObject) {
                for (key: String in error.keys().iterator()) {
                    return error.getString(key)
                }
            }
        }
        responseObject.getString("message")
    } catch (e: Exception) {
        if (null != e.message)
            e.message!!
        else
            context.getString(R.string.error)

        e.printStackTrace()
    }
    return ""
}