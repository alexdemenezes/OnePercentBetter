package com.example.onepercentbetter.domain.usecase.item

import com.example.onepercentbetter.data.repository.ItemRepository
import com.example.onepercentbetter.domain.model.item.Item

class GetItemByIdUseCaseImpl(
    private val itemRepository: ItemRepository
): GetItemByIdUseCase {
    override suspend fun execute(itemId: String): Item? {
        return itemRepository.getById(itemId)
    }
}