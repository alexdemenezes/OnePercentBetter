package com.example.onepercentbetter.presenter.screens.itemDetail

import com.example.onepercentbetter.domain.model.item.Item

data class ItemDetailUiState(
    var itemId: String? = null,
    var item: Item? = null
)