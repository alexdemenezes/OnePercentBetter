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
        MutableLiveData<ItemFormUiState>(ItemFormUiState(item = Item()))
    }

    fun getItemById(itemId: String?) {
        viewModelScope.launch {
            var item: Item? = null

            itemId?.let {
                item = getItemByIdUseCase.execute(itemId)
            }
            _uiState.value?.let { currentUiState ->
                _uiState.value = currentUiState.copy(
                    item = item ?: currentUiState.item,
                    isNewItem = item == null
                )
            }
        }
    }

    fun streamUiState(): LiveData<ItemFormUiState> = _uiState

    fun setItemTitle(title: String) {
        _uiState.value?.let { currentUiState ->
            currentUiState.item.title = title
            _uiState.value = currentUiState
        }
    }

    fun setItemDescription(description: String) {
        _uiState.value?.let { currentUiState ->
            currentUiState.item.description = description
            _uiState.value = currentUiState
        }
    }

    fun setItemDifficult(itemDifficulty: ItemDifficulty) {
        _uiState.value?.let { currentUiState ->
            currentUiState.item.difficulty = itemDifficulty
            _uiState.value = currentUiState
        }
    }

    fun setItemStatus(itemStatus: ItemStatus) {
        _uiState.value?.let { currentUiState ->
            currentUiState.item.status = itemStatus
            _uiState.value = currentUiState
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
                saveItemUseCase.execute(_uiState.value!!.item)
                _uiState.value?.showSuccessSnackbar = true
                Log.d("ItemFormViewModel", "showSuccess: ${_uiState.value?.showSuccessSnackbar}")
            } catch (err: Exception) {
                _uiState.value?.showFailureSnackbar = true
            }
        }
    }

    private fun update() {
        viewModelScope.launch {
            try {
                updateItemUseCase.execute(_uiState.value?.item!!)
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
                item = Item(),
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