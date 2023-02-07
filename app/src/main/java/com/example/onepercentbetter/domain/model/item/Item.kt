package com.example.onepercentbetter.domain.model.item

import com.example.onepercentbetter.data.database.entity.ItemEntity

data class Item(
    val id: String,
    var title: String,
    var description: String,
    var status: ItemStatus,
    var difficulty: ItemDifficulty,
    val createdAt: String
)

fun Item.toItemEntity() = ItemEntity(
    id = this.id,
    title =this.title,
    description = this.description,
    status = this.status.name,
    difficulty = this.difficulty.name,
    createdAt = this.createdAt,
)
