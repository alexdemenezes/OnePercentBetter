package com.example.onepercentbetter.domain.usecase.item

import com.example.onepercentbetter.data.repository.ItemRepository
import com.example.onepercentbetter.domain.model.item.Item

class GetItemsByTodayUseCaseImpl(
    private val itemRepository: ItemRepository
): GetItemsByTodayUseCase {
    override suspend fun execute(): List<Item> {
        return itemRepository.getTodayItems()
    }

}