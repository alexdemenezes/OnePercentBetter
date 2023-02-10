package com.example.onepercentbetter.presenter.screens.itemForm

import com.example.onepercentbetter.domain.model.item.Item

data class ItemFormUiState(
    var item: Item = Item(),
    var showInvalidFormMessage: Boolean = false
)
