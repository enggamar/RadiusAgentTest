package com.radiusagent.demo.common.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.radiusagent.demo.R

/**
 *This class is responsible for get and save  preference data
 * @constructor Create empty Radius pref manager
 */
internal class RadiusPrefManager {

    private val LAST_API_TIME_STAMP = "LAST_API_TIME_STAMP"
    private var preferences: SharedPreferences? = null

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    /**
     * Init Radius preference manager
     *This function is used to initialize Encrypted Shared Preference
     * @param appContext - appContext is the application context
     */
    fun initRadiusPreferenceManager(appContext: Context) {
        try {
            preferences = EncryptedSharedPreferences.create(
                "RadiusPreference",
                masterKeyAlias,
                appContext,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {
            preferences = appContext.getSharedPreferences(
                appContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE
            )
        }
    }

    /**
     * This function is used to return instance of RadiusPrefManager
     */
    object Instance {
        lateinit var instance: RadiusPrefManager
        val radiusPrefManager: RadiusPrefManager
            get() {
                if (this::instance.isInitialized.not()) {
                    instance = RadiusPrefManager()
                }
                return instance
            }
    }

    /**
     * This function is used to get last api timestamp
     */
    fun getLastApiTimeStamp(): Long {
        return preferences?.getLong(LAST_API_TIME_STAMP, 0L) ?: 0L
    }

    /**
     * This function is used to save last api timestamp
     */
    fun saveLastApiTimeStamp(timeStamp: Long) {
        preferences?.edit()?.putLong(LAST_API_TIME_STAMP, timeStamp)?.apply()

    }

}