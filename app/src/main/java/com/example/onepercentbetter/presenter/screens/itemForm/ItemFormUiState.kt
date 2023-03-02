package com.example.onepercentbetter.presenter.screens.itemForm

import com.example.onepercentbetter.domain.model.item.Item

data class ItemFormUiState(
    var isNewItem: Boolean = true,
    var showSuccessSnackbar: Boolean = false,
    var showFailureSnackbar: Boolean = false,
    var teste: Boolean = false
)
