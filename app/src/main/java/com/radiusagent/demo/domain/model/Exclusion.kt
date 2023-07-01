package com.radiusagent.demo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exclusion(
    val facility_id: String,
    val options_id: String
):Parcelable