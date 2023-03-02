package com.example.onepercentbetter.presenter.screens.home

import com.example.onepercentbetter.domain.model.item.Item


data class HomeUiState(
    val itemList: List<Item>,
    val date: String
)
