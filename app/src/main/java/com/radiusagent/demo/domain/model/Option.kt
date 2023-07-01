package com.radiusagent.demo.domain.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(
    val icon: String?, val id: String?, val name: String?, var isDisable : Boolean=false
) : Parcelable