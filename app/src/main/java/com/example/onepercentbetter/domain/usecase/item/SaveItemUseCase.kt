package com.example.onepercentbetter.domain.usecase.item

import com.example.onepercentbetter.domain.model.item.Item


interface SaveItemUseCase {
    suspend fun execute(item: Item): Unit
}