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

    private val _uiState: MutableLiveData<ItemFormUiState> by lazy {
        MutableLiveData<ItemFormUiState>(ItemFormUiState())
    }

    private val _item: MutableLiveData<Item> = MutableLiveData(Item())

    fun getItemById(itemId: String?) {
        viewModelScope.launch {
            var item: Item? = null
            itemId?.let {
                item = getItemByIdUseCase.execute(itemId)
            }
            _uiState.value?.let { currentUiState ->
                _uiState.value = currentUiState.copy(
                    isNewItem = item == null
                )
            }
            _item.value?.let {
                _item.value = item ?: it
            }
        }
    }

    fun streamUiState(): LiveData<ItemFormUiState> = _uiState
    fun streamItem(): LiveData<Item> = _item


    fun setItemFields(item: Item) {
        _item.value?.let {
            _item.value!!.title = item.title
            _item.value!!.description= item.description
            _item.value!!.status= item.status
            _item.value!!.difficulty = item.difficulty
        }
    }


    fun save() {
        if (_uiState.value?.isNewItem!!) {
            create()
        } else {
            update()
        }
    }


    private fun create() {
        viewModelScope.launch {
            try {
                saveItemUseCase.execute(_item.value!!)
                _uiState.value?.showSuccessSnackbar = true
            } catch (err: Exception) {
                _uiState.value?.showFailureSnackbar = true
            }
        }
    }

    private fun update() {
        viewModelScope.launch {
            try {
                updateItemUseCase.execute(_item.value!!)
                _uiState.value?.showSuccessSnackbar = true
            } catch (err: Exception) {
                _uiState.value?.showFailureSnackbar = true
            }

        }
    }

    fun doneShowingSuccessSnackbar() {
        _uiState.value?.showSuccessSnackbar = false
    }

    fun doneShowingFailureSnackbar() {
        _uiState.value?.showFailureSnackbar = false
    }

    fun restoreDefaultItem() {
        _uiState.value?.let { currentUiState ->
            _uiState.value = currentUiState.copy(
                isNewItem = false,
                showSuccessSnackbar = false,
                showFailureSnackbar = false
            )
        }
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