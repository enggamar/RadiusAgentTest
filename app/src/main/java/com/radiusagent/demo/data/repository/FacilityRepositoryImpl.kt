package com.radiusagent.demo.data.repository

import com.radiusagent.demo.common.base_response.BaseResponse
import com.radiusagent.demo.data.ApiService
import com.radiusagent.demo.domain.model.Facilities
import com.radiusagent.demo.domain.repository.FacilityRepositry
import retrofit2.Call

class FacilityRepositoryImpl constructor(private val api: ApiService) : FacilityRepositry {
    override fun callFacilitiesApi(): Call<Facilities?>? {
        return api.callFacilitiesApi()
    }
}