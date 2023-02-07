package com.example.onepercentbetter.data.repository

import com.example.onepercentbetter.data.database.AppDatabase
import com.example.onepercentbetter.data.database.entity.toItem
import com.example.onepercentbetter.domain.model.item.Item
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
        TODO("Not yet implemented")
    }

    override suspend fun insert(item: Item) {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Item? {
        TODO("Not yet implemented")
    }

}