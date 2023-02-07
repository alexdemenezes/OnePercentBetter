package com.example.onepercentbetter.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.onepercentbetter.domain.model.item.Item
import com.example.onepercentbetter.domain.model.item.ItemDifficulty
import com.example.onepercentbetter.domain.model.item.ItemStatus

@Entity(tableName = "item_table")
data class ItemEntity(
    @PrimaryKey
    val id: String,
    var title: String,
    var description: String,
    var status: String,
    var difficulty: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String
)

fun ItemEntity.toItem() = Item(
    id = this.id,
    title = this.title,
    description = this.description,
    status = ItemStatus.getStatusByName(this.status),
    difficulty = ItemDifficulty.getDifficultyByName(this.difficulty),
    createdAt = this.createdAt,
)
