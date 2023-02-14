package com.example.onepercentbetter.data.repository

import android.util.Log
import com.example.onepercentbetter.data.database.AppDatabase
import com.example.onepercentbetter.data.database.entity.toItem
import com.example.onepercentbetter.domain.model.item.Item
import com.example.onepercentbetter.domain.model.item.toItemEntity
import com.example.onepercentbetter.getToday

class ItemRepositoryImpl(
    private val appDatabase: AppDatabase
): ItemRepository {
    override suspend fun getTodayItems(): List<Item> {
        return appDatabase.itemDatabaseDao.getTodayItems(getToday()).map { itemEntity ->
            itemEntity.toItem()
        }
    }

    override suspend fun update(item: Item) {
        appDatabase.itemDatabaseDao.update(item = item.toItemEntity())
    }

    override suspend fun insert(item: Item) {
        appDatabase.itemDatabaseDao.insert(item.toItemEntity())
    }

    override suspend fun getById(id: String): Item? {
        return appDatabase.itemDatabaseDao.getById(id).toItem()
    }

}