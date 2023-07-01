package com.radiusagent.demo.domain.repository
import com.radiusagent.demo.common.base_response.BaseResponse
import com.radiusagent.demo.domain.model.Facilities
import retrofit2.Call

interface FacilityRepositry {
    fun callFacilitiesApi():  Call<Facilities?>?
}