package com.radiusagent.demo.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiusagent.demo.R
import com.radiusagent.demo.common.RadiusUtils
import com.radiusagent.demo.databinding.ItemOptionsBinding
import com.radiusagent.demo.domain.FacilityItemClickedListener
import com.radiusagent.demo.domain.OnItemClickedListener
import com.radiusagent.demo.domain.model.Facility
import com.radiusagent.demo.domain.model.Option
import com.radiusagent.demo.presentation.home.viewholder.OptionViewHolder

class OptionsAdapter(val listener: FacilityItemClickedListener) :
    RecyclerView.Adapter<OptionViewHolder>(), OnItemClickedListener {
    var options = ArrayList<Option>()
    var facilityId: String? = ""
    private var checkedPosition = -1
    lateinit var mBinding: ItemOptionsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        mBinding = ItemOptionsBinding.inflate(LayoutInflater.from(parent.context))
        return OptionViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return options.size
    }


    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val viewHolder = holder as OptionViewHolder
        viewHolder.bind(options[position], position, checkedPosition, this)
    }

    /**
     * THis function is used to notify option adapter
     */
    @SuppressLint("NotifyDataSetChanged")
    fun notifyListData(facility: Facility, facilityPosition: Int) {
        this.options = facility.options!!
        this.facilityId = facility.facility_id
        notifyDataSetChanged()
    }

    /**
     * THis function is called, when used clicked on an option of facility
     */
    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClicked(option: Option, position: Int) {
        checkedPosition = position
        if (!option.isDisable) listener.onItemClicked(options, facilityId, option.id)
        else {
            RadiusUtils.showToast(
                mBinding.root.context,
                option.name.plus(mBinding.root.context.getString(R.string.facility_is_not_allowed_with_selected_option))
            )

        }
    }
}
