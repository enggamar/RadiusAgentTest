package com.radiusagent.demo.data

import com.radiusagent.demo.common.base_response.BaseResponse
import com.radiusagent.demo.domain.model.Facilities
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("iranjith4/ad-assignment/db")
    fun callFacilitiesApi(): Call<Facilities?>?

}