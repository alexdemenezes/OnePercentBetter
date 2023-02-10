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
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch

class ItemFormViewModel(
    private val itemId: String?,
    private val getItemByIdUseCase: GetItemByIdUseCase
) : ViewModel() {

    private val _uiState: MutableLiveData<ItemFormUiState> by lazy {
        MutableLiveData<ItemFormUiState>(ItemFormUiState(item = Item()))
    }

    init {
        itemId?.let {
            viewModelScope.launch {
                getItemById()
            }
        }
    }

    private suspend fun getItemById() {
        var item = getItemByIdUseCase.execute(itemId!!)
        _uiState.value?.let { currentUiState ->
            _uiState.value = currentUiState.copy(
                item = item ?: currentUiState.item
            )
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

    fun isValidForm(): Boolean {
        val isValidTitle = _uiState.value?.item!!.title.isNotEmpty()
        Log.d(
            "ItemFormViewModel",
            "title: ${_uiState.value?.item!!.title}, " +
                    "isInvalid: ${!isValidTitle}"
        )
        _uiState.value?.showInvalidFormMessage = !isValidTitle
        return isValidTitle
    }

    fun doneShowingInvalidFormMessage() {
        _uiState.value?.showInvalidFormMessage = false
    }


    fun save() {
        // will save item
        // use case to save item on db
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val itemId: String?,
        private val getItemByIdUseCase: GetItemByIdUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ItemFormViewModel(itemId, getItemByIdUseCase) as T
        }
    }
}