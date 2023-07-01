package com.radiusagent.demo.domain

import com.radiusagent.demo.domain.model.Facilities
import com.radiusagent.demo.domain.model.Option

interface OnItemClickedListener {
    fun onItemClicked(option: Option,position:Int)
}