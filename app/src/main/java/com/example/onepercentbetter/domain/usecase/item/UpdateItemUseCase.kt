package com.example.onepercentbetter.domain.usecase.item

import com.example.onepercentbetter.domain.model.item.Item

interface UpdateItemUseCase {
    suspend fun execute(item: Item): Unit
}