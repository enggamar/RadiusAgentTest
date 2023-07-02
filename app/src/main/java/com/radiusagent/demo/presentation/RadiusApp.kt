package com.radiusagent.demo.presentation
import android.app.Application
import com.radiusagent.demo.common.preference.RadiusPrefManager
import com.radiusagent.demo.data.database.DBHelper


class RadiusApp : Application() {

    override fun onCreate() {
        super.onCreate()
        RadiusPrefManager.Instance.radiusPrefManager.initRadiusPreferenceManager(applicationContext)
        DBHelper.getInstance(applicationContext)
    }
}