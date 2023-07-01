package com.radiusagent.demo.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.radiusagent.demo.common.constants.DbConstant
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = DbConstant.FACILITIES)
data class Facilities(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "facilities", typeAffinity = ColumnInfo.TEXT) val facilities: ArrayList<Facility>,
    @ColumnInfo(
        name = "exclusions", typeAffinity = ColumnInfo.TEXT
    ) val exclusions: ArrayList<ArrayList<Exclusion>>,
):Parcelable