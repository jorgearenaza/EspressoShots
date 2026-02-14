package com.jorgearenaza.espressoshots.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jorgearenaza.espressoshots.data.db.dao.BeanDao
import com.jorgearenaza.espressoshots.data.db.dao.GrinderDao
import com.jorgearenaza.espressoshots.data.db.dao.ProfileDao
import com.jorgearenaza.espressoshots.data.db.dao.ShotDao
import com.jorgearenaza.espressoshots.data.db.entity.BeanEntity
import com.jorgearenaza.espressoshots.data.db.entity.GrinderEntity
import com.jorgearenaza.espressoshots.data.db.entity.ProfileEntity
import com.jorgearenaza.espressoshots.data.db.entity.ShotEntity

@Database(
    entities = [
        BeanEntity::class,
        GrinderEntity::class,
        ProfileEntity::class,
        ShotEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun beanDao(): BeanDao
    abstract fun grinderDao(): GrinderDao
    abstract fun profileDao(): ProfileDao
    abstract fun shotDao(): ShotDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "espresso_shots_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
