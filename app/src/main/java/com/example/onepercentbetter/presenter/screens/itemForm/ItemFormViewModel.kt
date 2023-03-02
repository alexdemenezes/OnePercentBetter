package com.example.onepercentbetter.presenter.screens.itemForm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.onepercentbetter.domain.model.item.Item
import com.example.onepercentbetter.domain.model.item.ItemDifficulty
import com.example.onepercentbetter.domain.model.item.ItemStatus
import com.example.onepercentbetter.domain.usecase.item.GetItemByIdUseCase
import com.example.onepercentbetter.domain.usecase.item.SaveItemUseCase
import com.example.onepercentbetter.domain.usecase.item.UpdateItemUseCase
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch

class ItemFormViewModel(
    private val getItemByIdUseCase: GetItemByIdUseCase,
    private val saveItemUseCase: SaveItemUseCase,
    private val updateItemUseCase: UpdateItemUseCase
) : ViewModel() {

    private val _isNewItem: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _item: MutableLiveData<Item> = MutableLiveData(Item())
    val item: LiveData<Item>
        get() = _item

    private val _showSuccess: MutableLiveData<Boolean> = MutableLiveData(false)
    val showSuccess: LiveData<Boolean>
        get() = _showSuccess

    private val _showFailure: MutableLiveData<Boolean> = MutableLiveData(false)
    val showFailure: LiveData<Boolean>
        get() = _showFailure

    fun getItemById(itemId: String?) {
        viewModelScope.launch {
            var item: Item? = null
            itemId?.let {
                item = getItemByIdUseCase.execute(itemId)
            }
            _isNewItem.value = item == null
            _item.value?.let {
                _item.value = item ?: it
            }
        }
    }

    fun setItemFields(item: Item) {
        _item.value?.let {
            _item.value!!.title = item.title
            _item.value!!.description= item.description
            _item.value!!.status= item.status
            _item.value!!.difficulty = item.difficulty
        }
    }


    fun save() {
        if (_isNewItem.value!!) {
            create()
        } else {
            update()
        }
    }


    private fun create() {
        viewModelScope.launch {
            try {
                saveItemUseCase.execute(_item.value!!)
                _showSuccess.value = true
            } catch (err: Exception) {
                _showFailure.value = true
            }
        }
    }

    private fun update() {
        viewModelScope.launch {
            try {
                updateItemUseCase.execute(_item.value!!)
                _showSuccess.value = true
            } catch (err: Exception) {
                _showFailure.value = true
            }

        }
    }

    fun doneShowingSuccessSnackbar() {
        _showSuccess.value = false
    }

    fun doneShowingFailureSnackbar() {
        _showFailure.value = false
    }

    fun restoreDefaultItem() {
        //
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val getItemByIdUseCase: GetItemByIdUseCase,
        private val saveItemUseCase: SaveItemUseCase,
        private val updateItemUseCase: UpdateItemUseCase,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ItemFormViewModel(
                getItemByIdUseCase,
                saveItemUseCase,
                updateItemUseCase
            ) as T
        }
    }
}