package com.example.onepercentbetter.domain.usecase.item

import com.example.onepercentbetter.domain.model.item.Item

interface GetItemByIdUseCase {
    suspend fun execute(itemId: String): Item?
}