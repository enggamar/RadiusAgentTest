package com.radiusagent.demo.common

import com.radiusagent.demo.domain.model.Exclusion
import com.radiusagent.demo.domain.model.Facility
import com.radiusagent.demo.domain.model.Option

object FilterHelper {

    /**
     * This function is used to filter the facilities based on exclusion list
     */
    fun filterFacilitiesOptions(
        selectedFacilityId: String?,
        selectedOptionId: String?,
        mExclusionList: ArrayList<ArrayList<Exclusion>>?,
        mFacilityList: ArrayList<Facility>?
    ): ArrayList<Facility> {
        if (mExclusionList != null) {
            resetList(mFacilityList)
            for (mlist: ArrayList<Exclusion> in mExclusionList) {
                for (exclusion: Exclusion in mlist) {
                    if (exclusion.facility_id.equals(
                            selectedFacilityId, true
                        ) && exclusion.options_id.equals(selectedOptionId, true)
                    ) {
                        updateInFacilitiesOption(
                            mlist, mFacilityList, selectedFacilityId, selectedOptionId
                        )
                        break
                    }
                }
            }
        }
        return mFacilityList!!
    }

    /**
     * This function is used to make all disable variable as false
     */
    private fun resetList(mFacilityList: java.util.ArrayList<Facility>?) {
        mFacilityList?.forEach {
            it.options?.forEach { option: Option ->
                option.isDisable = false
            }
        }
    }

    private fun updateInFacilitiesOption(
        mExclusionList: List<Exclusion>,
        mFacilityList: List<Facility>?,
        selectedFacilityId: String?,
        selectedOptionId: String?,
    ) {
        for (exclusion: Exclusion in mExclusionList) {
            if (!exclusion.facility_id.equals(
                    selectedFacilityId, true
                ) && !exclusion.options_id.equals(selectedOptionId, true)
            ) {
                mFacilityList?.filter { it.facility_id.equals(exclusion.facility_id, true) }
                    ?.forEach {
                        it.options?.filter { option -> option.id == exclusion.options_id }
                            ?.find { option: Option -> option.id == exclusion.options_id }?.isDisable =
                            true
                        mFacilityList.filter { it.facility_id.equals(exclusion.facility_id, true) }
                            .forEach {
                                it.options?.filter { option -> option.id != exclusion.options_id }
                                    ?.find { option: Option -> option.id != exclusion.options_id }?.isDisable =
                                    false
                            }
                    }
            }
        }
    }

}