package com.abora.perfectobase.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.abora.perfectobase.app.Application.Companion.context
import com.abora.perfectobase.di.module.classesModule
import com.abora.perfectobase.di.module.networkModule
import com.abora.perfectobase.di.module.preferencesModule
import com.abora.perfectobase.di.module.viewModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class Application : Application() {

    companion object {
        var language: String = "en"
        private var context: Context? = null
    }

    val sharedPreferences: SharedPreferences by inject()

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Application)
            modules(networkModule, preferencesModule, viewModelModule, classesModule)
        }
        language = if (sharedPreferences.getString("lang", "en") == "en") {
            "en"
        } else {
            "ar"
        }
        if (!sharedPreferences.contains("darkMode"))
            sharedPreferences.edit().putBoolean("darkMode", false).apply()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }

    fun getAppContext(): Context? {
        return context
    }




}