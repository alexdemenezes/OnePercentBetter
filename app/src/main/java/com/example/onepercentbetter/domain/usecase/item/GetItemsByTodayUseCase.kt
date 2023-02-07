package com.example.onepercentbetter.domain.usecase.item

import com.example.onepercentbetter.domain.model.item.Item

interface GetItemsByTodayUseCase {
    suspend fun execute(): List<Item>
}