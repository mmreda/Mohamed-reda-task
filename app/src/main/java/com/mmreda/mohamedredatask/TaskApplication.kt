package com.mmreda.mohamedredatask

import android.app.Application
import android.content.Context
import com.mmreda.mohamedredatask.utils.SharedPreferenceManger
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TaskApplication: Application() {


    companion object {
        lateinit var appContext: Context
        lateinit var sharedPreferencesManager: SharedPreferenceManger
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        sharedPreferencesManager = SharedPreferenceManger()

        Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}