package com.radiusagent.demo.data.remote

import com.google.gson.GsonBuilder
import com.radiusagent.demo.data.ApiService
import com.radiusagent.demo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This class is used for setup HttpClient and retrofit
 */
internal class NetworkManager private constructor() {

    private var apiService: ApiService? = null
    private var httpClient: OkHttpClient.Builder
    private lateinit var retrofitBuilder: Retrofit.Builder


    init {
        httpClient = getHttpClient()
        apiService = getRetrofitService()
    }

    /**
     * This function is used for getting ApiManager instance
     */
    object Instance {
        private lateinit var instance: NetworkManager

        @Synchronized
        fun getInstance(): NetworkManager {
            if (this::instance.isInitialized.not()) {
                instance = NetworkManager()
            }
            return instance
        }
    }

    /**
     * This function is used for getting ApiService instance
     */
    fun getApiClientInstance(): ApiService? {
        if (apiService == null) {
            apiService = getRetrofitService()
        }
        return apiService;
    }

    /**
     * This function is used for configuring retrofit and return ApiService
     */
    private fun getRetrofitService(): ApiService? {
        retrofitBuilder = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        val retrofit: Retrofit = retrofitBuilder.client(httpClient.build()).build()
        return retrofit.create<ApiService>(ApiService::class.java)
    }

    /**
     * This function is used for configuring the HTTPClient
     */
    private fun getHttpClient(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(getLoggingInterceptor())

        builder.readTimeout(30000, TimeUnit.MILLISECONDS)
        builder.writeTimeout(30000, TimeUnit.MILLISECONDS)
        return builder
    }

    /**
     * This function is used for configuring Http Interceptor for Logs
     */
    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) HttpLoggingInterceptor(CustomHttpLogger()).setLevel(
            HttpLoggingInterceptor.Level.BODY
        ) else HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.NONE
        )
    }

}