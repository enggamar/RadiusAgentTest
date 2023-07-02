package com.radiusagent.demo.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.radiusagent.demo.common.FilterHelper
import com.radiusagent.demo.databinding.ItemFacilitiesBinding
import com.radiusagent.demo.domain.FacilityItemClickedListener
import com.radiusagent.demo.domain.OnItemClickedListener
import com.radiusagent.demo.domain.model.Exclusion
import com.radiusagent.demo.domain.model.Facilities
import com.radiusagent.demo.domain.model.Facility
import com.radiusagent.demo.domain.model.Option
import com.radiusagent.demo.presentation.home.viewholder.HomeViewHolder

class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>(), FacilityItemClickedListener {
    var mList = ArrayList<Facility>()
    var mExclusionList: ArrayList<ArrayList<Exclusion>>? = null
    var facilities: Facilities? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val mBinding = ItemFacilitiesBinding.inflate(LayoutInflater.from(parent.context))
        return HomeViewHolder(mBinding, this)
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val viewHolder = holder as HomeViewHolder
        viewHolder.bind(mList[position], position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notifyListData(facilities: Facilities) {
        this.facilities = facilities
        this.mList = facilities.facilities
        this.mExclusionList = facilities.exclusions
        notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClicked(
        option: ArrayList<Option>?, facilityId: String?, selectedOptionId: String?
    ) {
        this.mList = FilterHelper.filterFacilitiesOptions(
            facilityId, selectedOptionId, mExclusionList, facilities?.facilities
        )
        notifyDataSetChanged()
    }


}
