package com.radiusagent.demo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.radiusagent.demo.data.database.dao.RadiusDao
import com.radiusagent.demo.domain.model.Facilities

@Database(
    entities = [Facilities::class], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DBHelper : RoomDatabase() {

    abstract fun getRadiusDao(): RadiusDao

    companion object {
        // Singleton instance of the database
        private var INSTANCE: DBHelper? = null

        // Method to get the database instance
        fun getInstance(context: Context): DBHelper {
            if (INSTANCE == null) {
                synchronized(DBHelper::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, DBHelper::class.java, "app_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}