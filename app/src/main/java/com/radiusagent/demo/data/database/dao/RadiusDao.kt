package com.radiusagent.demo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.radiusagent.demo.common.constants.DbConstant
import com.radiusagent.demo.domain.model.Facilities

@Dao
interface RadiusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(facilities: Facilities?)

    @Query("SELECT * FROM " + DbConstant.FACILITIES)
    fun getProducts(): LiveData<Facilities?>?
}