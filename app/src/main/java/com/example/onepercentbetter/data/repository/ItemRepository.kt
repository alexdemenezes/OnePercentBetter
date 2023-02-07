package com.example.onepercentbetter.data.repository

import com.example.onepercentbetter.domain.model.item.Item

interface ItemRepository {
    suspend fun getTodayItems(): List<Item>
    suspend fun update(item: Item)
    suspend fun insert(item: Item)
    suspend fun getById(id: String): Item?
}