package com.example.onepercentbetter.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.onepercentbetter.data.database.entity.ItemEntity

@Dao
interface ItemDatabaseDao {
    @Insert
    suspend fun insert(item: ItemEntity)

    @Update
    suspend fun update(item: ItemEntity)

    @Query("SELECT * FROM item_table WHERE id = :id")
    suspend fun getById(id: String): ItemEntity

    @Query("SELECT * FROM item_table WHERE created_at = :today")
    suspend fun getTodayItems(today: String): List<ItemEntity>
}