package com.radiusagent.demo.presentation
import android.app.Application
import com.radiusagent.demo.data.database.DBHelper


class RadiusApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DBHelper.getInstance(applicationContext)
    }
}