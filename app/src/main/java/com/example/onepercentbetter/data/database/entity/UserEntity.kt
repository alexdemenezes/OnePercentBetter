package com.example.onepercentbetter.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.onepercentbetter.domain.model.user.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    val email: String,
    val password: String,
    @ColumnInfo(name = "daily_goal")
    val dailyGoal: Int
)

fun UserEntity.toUser() = User(
    id = this.id,
    fullName = this.fullName,
    email = this.email,
    password = this.password,
    dailyGoal = this.dailyGoal
)
