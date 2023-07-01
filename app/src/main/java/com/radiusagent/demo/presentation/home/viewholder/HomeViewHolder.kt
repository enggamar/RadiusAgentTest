package com.radiusagent.demo.presentation.home.viewholder

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radiusagent.demo.databinding.ItemFacilitiesBinding
import com.radiusagent.demo.domain.FacilityItemClickedListener
import com.radiusagent.demo.domain.OnItemClickedListener
import com.radiusagent.demo.domain.model.Facility
import com.radiusagent.demo.domain.model.Option
import com.radiusagent.demo.presentation.home.adapter.OptionsAdapter


class HomeViewHolder(val mBinding: ItemFacilitiesBinding,val listener: FacilityItemClickedListener) : RecyclerView.ViewHolder(mBinding.root){
    private lateinit var mOptionAdapter: OptionsAdapter

    init {
        setAdapter()
    }

    private fun setAdapter() {
        mOptionAdapter = OptionsAdapter(listener)
        mBinding.rvOptions.layoutManager = GridLayoutManager(mBinding.root.context, 3)
        mBinding.rvOptions.adapter = mOptionAdapter

    }

    fun bind(facility: Facility, facilityPosition: Int) {
        setDyanmicLayoutParms()
        mBinding.tvTitle.text = facility.name
        mOptionAdapter.notifyListData(facility,facilityPosition)
    }

    private fun setDyanmicLayoutParms() {
        val params = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(15, 15, 15, 15)
        mBinding.root.layoutParams = params
    }
}
