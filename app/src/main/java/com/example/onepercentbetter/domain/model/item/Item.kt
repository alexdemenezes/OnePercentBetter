package com.example.onepercentbetter.domain.model.item

import com.example.onepercentbetter.data.database.entity.ItemEntity
import com.example.onepercentbetter.getToday
import java.util.UUID

data class Item(
    val id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var description: String = "",
    var status: ItemStatus = ItemStatus.LEARNED,
    var difficulty: ItemDifficulty = ItemDifficulty.EASY,
    val createdAt: String = getToday()
)

fun Item.toItemEntity() = ItemEntity(
    id = this.id,
    title =this.title,
    description = this.description,
    status = this.status.name,
    difficulty = this.difficulty.name,
    createdAt = this.createdAt,
)
