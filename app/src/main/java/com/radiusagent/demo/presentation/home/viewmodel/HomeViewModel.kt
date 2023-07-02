package com.radiusagent.demo.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.radiusagent.demo.common.RadiusUtils
import com.radiusagent.demo.common.preference.RadiusPrefManager
import com.radiusagent.demo.domain.model.Facilities
import com.radiusagent.demo.domain.use_case.facilities.FacilityUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


open class HomeViewModel(private val useCase: FacilityUseCase) : ViewModel() {

    private val facilitiesLiveData = MediatorLiveData<Facilities?>()

    /**
     * This function is used to call get facilities api from network
     */
    fun getFacilitiesData() {
        useCase.callFacilitiesApi()
    }

    /**
     * This function is to get the live data observer of facility DB table
     */
    fun getFacilitiesLiveData(): LiveData<Facilities?>? {
        return useCase.getFacilitiesLiveData()
    }

    fun is24hoursCompleted(): Boolean {
        return (RadiusPrefManager.Instance.radiusPrefManager.getLastApiTimeStamp() == 0L || RadiusUtils.isMoreThan24Hours(
            RadiusPrefManager.Instance.radiusPrefManager.getLastApiTimeStamp()
        ))
    }

}