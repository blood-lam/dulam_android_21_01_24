package com.lamdx.smartmovie

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.lamdx.smartmovie.model.AppConfiguration
import com.lamdx.smartmovie.utils.Constant
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SmartMovieApplication : Application() {

    var preferenceFileKey: String? = null
    var appConfiguration: AppConfiguration? = null

    override fun onCreate() {
        super.onCreate()
        preferenceFileKey = getString(R.string.preference_file_key)
        appConfiguration = loadConfigLocal()
    }

    private fun loadConfigLocal(): AppConfiguration? {
        val sharedPreferences = applicationContext.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE)
        val configJson = sharedPreferences.getString(Constant.APP_CONFIG, null)
        return Gson().fromJson(configJson, AppConfiguration::class.java)
    }

}