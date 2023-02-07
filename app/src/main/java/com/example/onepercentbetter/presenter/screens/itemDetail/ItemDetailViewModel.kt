package com.example.onepercentbetter.presenter.screens.itemDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onepercentbetter.domain.model.item.Item

class ItemDetailViewModel(
    private val itemId: String
) : ViewModel() {
    private val _item = MutableLiveData<Item>()

    val item: LiveData<Item>
        get() = _item

    init {
        // get item by id from database
    }


}