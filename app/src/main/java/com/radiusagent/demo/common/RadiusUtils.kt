package com.radiusagent.demo.common

import android.content.Context
import android.widget.Toast
import com.radiusagent.demo.R

object RadiusUtils {

    fun getImage(imgName: String): Int {
        var image = R.drawable.apartment
        when (imgName) {
            "apartment" -> {
                image = R.drawable.apartment
            }
            "condo" -> {
                image = R.drawable.condo
            }
            "boat" -> {
                image = R.drawable.boat
            }
            "land" -> {
                image = R.drawable.land
            }
            "rooms" -> {
                image = R.drawable.rooms
            }
            "no-room" -> {
                image = R.drawable.no_room
            }
            "swimming" -> {
                image = R.drawable.swimming
            }
            "garden" -> {
                image = R.drawable.garden
            }
            "garage" -> {
                image = R.drawable.garage
            }
        }
        return image
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
