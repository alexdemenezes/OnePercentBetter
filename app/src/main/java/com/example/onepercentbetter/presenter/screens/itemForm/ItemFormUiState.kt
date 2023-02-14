package com.example.onepercentbetter.presenter.screens.itemForm

import com.example.onepercentbetter.domain.model.item.Item

data class ItemFormUiState(
    var item: Item = Item(),
    var isNewItem: Boolean = true,
    var showInvalidFormMessage: Boolean = false,
    var showSuccessSnackbar: Boolean = false,
    var showFailureSnackbar: Boolean = false,
)
