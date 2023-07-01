package com.radiusagent.demo.domain

import com.radiusagent.demo.domain.model.Facilities
import com.radiusagent.demo.domain.model.Option

interface FacilityItemClickedListener {
    fun onItemClicked(option: ArrayList<Option>?,facility_id:String?,selectedOptionId:String?)
}