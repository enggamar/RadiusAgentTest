package com.radiusagent.demo.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Facility(
    val facility_id: String?,
    val name: String?,
    var options: ArrayList<Option>?
) : Parcelable