package com.example.onepercentbetter.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.onepercentbetter.data.database.entity.UserEntity

@Dao
interface UserDatabaseDao {
    @Insert
    suspend fun create(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE id = :id")
    suspend fun getById(id: String): UserEntity

    @Query("DELETE FROM user_table WHERE id = :id")
    suspend fun delete(id: String)
}