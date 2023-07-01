package com.radiusagent.demo.presentation.home.viewholder

import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.radiusagent.demo.R
import com.radiusagent.demo.common.RadiusUtils
import com.radiusagent.demo.databinding.ItemOptionsBinding
import com.radiusagent.demo.domain.OnItemClickedListener
import com.radiusagent.demo.domain.model.Option

class OptionViewHolder(val mBinding: ItemOptionsBinding) : RecyclerView.ViewHolder(mBinding.root) {


    fun bind(option: Option, position: Int, checkedPosition: Int, listener: OnItemClickedListener) {
        mBinding.tvTitle.text = option.name
        mBinding.ivSelect.visibility = View.GONE

        mBinding.tvTitle.setTextColor(
            ContextCompat.getColor(
                mBinding.tvTitle.context,
                R.color.text_color_blue
            )
        )
        if (option.icon != null) mBinding.img.setBackgroundResource(RadiusUtils.getImage(option.icon))

        if (checkedPosition == -1) {
            setDefaultBg()
        } else {
            if (checkedPosition == adapterPosition && !option.isDisable) {
                setSelectedBg()
                mBinding.ivSelect.visibility = View.VISIBLE
            } else {
                setDefaultBg()
            }
        }
        setEnableDisableBg(option)
        setOnClickListener(listener, option, position)
    }

    /**
     * This function is used to setup onclick listener in cardview
     */
    private fun setOnClickListener(
        listener: OnItemClickedListener,
        option: Option,
        position: Int
    ) {
        mBinding.cvContainer.setOnClickListener {
            listener.onItemClicked(option, position)
        }
    }

    /**
     * This function is used to set alpha =1.0f if option available
     */
    private fun setEnableDisableBg(option: Option) {
        if (option.isDisable) {
            mBinding.cvContainer.alpha = 0.6f

        } else {
            mBinding.cvContainer.alpha = 1.0f
        }
    }

    /**
     * This function is used to set bg color for selected background
     */
    private fun setSelectedBg() {
        mBinding.cvContainer.setCardBackgroundColor(
            ContextCompat.getColor(
                mBinding.img.context, R.color.purple_700
            )
        )
        mBinding.tvTitle.setTextColor(
            ContextCompat.getColor(
                mBinding.tvTitle.context,
                R.color.white
            )
        )

    }

    /**
     * This function is used to set default background
     */
    private fun setDefaultBg() {
        mBinding.cvContainer.setCardBackgroundColor(
            ContextCompat.getColor(
                mBinding.img.context, R.color.white
            )
        )
    }
}
