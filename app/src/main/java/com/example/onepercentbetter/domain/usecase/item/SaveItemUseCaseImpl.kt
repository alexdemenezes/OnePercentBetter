package com.example.onepercentbetter.domain.usecase.item

import com.example.onepercentbetter.data.repository.ItemRepository
import com.example.onepercentbetter.domain.model.item.Item

class SaveItemUseCaseImpl(
    private val itemRepository: ItemRepository
): SaveItemUseCase {
    override suspend fun execute(item: Item) {
        itemRepository.insert(item)
    }
}