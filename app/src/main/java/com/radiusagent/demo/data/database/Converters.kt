package com.radiusagent.demo.data.database
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.radiusagent.demo.domain.model.Exclusion
import com.radiusagent.demo.domain.model.Facility

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromFacilitiesList(facilitiesList: ArrayList<Facility>): String {
        return gson.toJson(facilitiesList)
    }

    @TypeConverter
    fun toFacilitiesList(facilitiesListString: String): ArrayList<Facility> {
        val listType = object : TypeToken<ArrayList<Facility>>() {}.type
        return gson.fromJson(facilitiesListString, listType)
    }

    @TypeConverter
    fun fromExclusionsList(exclusionsList: ArrayList<ArrayList<Exclusion>>): String {
        return gson.toJson(exclusionsList)
    }

    @TypeConverter
    fun toExclusionsList(exclusionsListString: String): ArrayList<ArrayList<Exclusion>> {
        val listType = object : TypeToken<ArrayList<ArrayList<Exclusion>>>() {}.type
        return gson.fromJson(exclusionsListString, listType)
    }
}
