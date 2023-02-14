package com.example.onepercentbetter.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.onepercentbetter.domain.model.progress.Progress

@Entity(tableName = "progress_table")
data class ProgressEntity(
    @PrimaryKey
    val id: String,
    var learned: Int,
    var improved: Int,
    @ColumnInfo(name = "current_streak")
    var currentStreak: Int,
    @ColumnInfo(name = "biggest_streak")
    var biggestStreak: Int
)

fun ProgressEntity.toProgress() = Progress(
    id = this.id,
    learned = this.learned,
    improved = this.improved,
    currentStreak = this.currentStreak,
    biggestStreak = this.biggestStreak
)
