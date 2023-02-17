package com.example.onepercentbetter.domain.model.user

import com.example.onepercentbetter.data.database.entity.UserEntity
import java.util.UUID

data class User(
    var id: String = UUID.randomUUID().toString(),
    var fullName: String = "",
    var email: String = "",
    var password: String = "",
    var dailyGoal: Int = 0
)

fun User.toUserEntity() = UserEntity(
    id = this.id,
    fullName = this.fullName,
    email = this.email,
    password = this.password,
    dailyGoal = this.dailyGoal
)
