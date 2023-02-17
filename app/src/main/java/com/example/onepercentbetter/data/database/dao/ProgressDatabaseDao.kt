package com.example.onepercentbetter.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.onepercentbetter.data.database.entity.ProgressEntity

@Dao
interface ProgressDatabaseDao {
    @Insert
    suspend fun insert(progress: ProgressEntity)

    @Update
    suspend fun update(progress: ProgressEntity)

    @Query("SELECT * FROM progress_table WHERE id = :id")
    suspend fun getUserProgress(id: String): ProgressEntity
}