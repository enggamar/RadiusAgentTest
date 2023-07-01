package com.radiusagent.demo.common.base_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("statusCode")
    @Expose
    var statusCode = 0

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: T? = null
        private set

    @SerializedName("type")
    @Expose
    private val type: String? = null

}