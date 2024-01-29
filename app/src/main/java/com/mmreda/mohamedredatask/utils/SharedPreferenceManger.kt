package com.mmreda.mohamedredatask.utils

import android.content.Context
import android.content.SharedPreferences
import com.mmreda.mohamedredatask.TaskApplication

class SharedPreferenceManger {
    private val sharedPreferences: SharedPreferences =
        TaskApplication.appContext.getSharedPreferences(
            sharedPreferencesKey,
            Context.MODE_PRIVATE
        )
    private val editor = sharedPreferences.edit()

    var userToken: String
        get() = getString(userTokenKey)
        set(token) {
            editor.putString(userTokenKey, "Bearer $token").apply()
        }


    var userName: String
        get() = getString(userNameKey)
        set(value) {
            editor.putString(userNameKey, value).apply()
        }

    var userEmail: String
        get() = getString(userEmailKey)
        set(value) {
            editor.putString(userEmailKey, value).apply()
        }

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(isLoggedInKey, false)
        set(value) {
            editor.putBoolean(isLoggedInKey, value).apply()
        }

    private fun getString(key: String): String {
        sharedPreferences.getString(key, "").let { s ->
            return if (s.isNullOrBlank())
                ""
            else
                s
        }
    }


    private fun getInt(key: String): Int {
        sharedPreferences.getInt(key, 0).let { s ->
            return s ?: 0
        }
    }


    companion object {
        private const val sharedPreferencesKey = "USERDATA"

        private const val userTokenKey = "API_TOKEN"
        private const val userNameKey = "USERNAME"
        private const val userEmailKey = "USEREMAIL"
        private const val isLoggedInKey = "LOGIN"
    }
}