package com.ayaabdelaal.elevate.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ayaabdelaal.elevate.model.Goal
import com.ayaabdelaal.elevate.model.Item
import com.ayaabdelaal.elevate.model.data.Converters

@Database(entities = [Item::class,Goal::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ElevateDatabase : RoomDatabase() {
    abstract fun itemDao() : ItemDao
    abstract fun goalDao(): GoalDao

    companion object {
        @Volatile
        private var INSTANCE: ElevateDatabase? = null
        fun getDatabase(context: Context): ElevateDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ElevateDatabase::class.java,
                    "elevate_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
