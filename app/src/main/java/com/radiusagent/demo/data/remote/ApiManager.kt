package com.radiusagent.demo.data.remote

import com.radiusagent.demo.data.ApiService

/**
 * This class is used for calling API's
 */
class ApiManager private constructor() {

   private var apiService: ApiService? = null

    /**
     * init block initilaize the apiclient
     */
    init {
        apiService = NetworkManager.Instance.getInstance().getApiClientInstance();
    }

    /**
     * This function is used to return instance of Network Manager
     */
    object Instance {
        private lateinit var instance: ApiManager
        val apiManagerInstance: ApiManager
            get() {
                if (this::instance.isInitialized.not()) {
                    instance = ApiManager()
                }
                return instance
            }
    }

    fun getApiService(): ApiService? {
        return if (apiService != null) apiService
        else{
            Instance.apiManagerInstance.apiService
        }
    }


}