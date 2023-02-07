package com.example.onepercentbetter.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onepercentbetter.data.database.dao.ItemDatabaseDao
import com.example.onepercentbetter.data.database.entity.ItemEntity


@Database(
    entities = [ItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val itemDatabaseDao: ItemDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Wrapping the code to get the database into synchronized means that only
        // one thread of execution at a time can enter this block of code, which makes
        // sure the database only gets initialized once.

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "one_percent_better_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}












