package com.example.onepercentbetter.domain.usecase.item

import com.example.onepercentbetter.data.repository.ItemRepository
import com.example.onepercentbetter.domain.model.item.Item

class UpdateItemUseCaseImpl(
    private val itemRepository: ItemRepository
): UpdateItemUseCase {
    override suspend fun execute(item: Item) {
        itemRepository.update(item)
    }
}