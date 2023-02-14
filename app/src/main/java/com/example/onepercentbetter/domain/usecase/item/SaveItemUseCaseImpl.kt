package com.example.onepercentbetter.domain.usecase.item

import android.util.Log
import com.example.onepercentbetter.data.repository.ItemRepository
import com.example.onepercentbetter.domain.model.item.Item

class SaveItemUseCaseImpl(
    private val itemRepository: ItemRepository
): SaveItemUseCase {
    override suspend fun execute(item: Item) {
        if (item.description.isNullOrBlank()) {
            item.description = "--"
        }
        itemRepository.insert(item)
    }
}