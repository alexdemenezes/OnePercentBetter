package com.example.onepercentbetter.presenter.screens.itemDetail

import android.util.Log
import androidx.lifecycle.*
import com.example.onepercentbetter.domain.model.item.Item
import com.example.onepercentbetter.domain.usecase.item.GetItemByIdUseCase
import com.example.onepercentbetter.domain.usecase.item.GetItemsByTodayUseCase
import com.example.onepercentbetter.presenter.screens.home.HomeViewModel
import kotlinx.coroutines.launch

class ItemDetailViewModel(
    private val getItemByIdUseCase: GetItemByIdUseCase
) : ViewModel() {
    private val _uiState: MutableLiveData<ItemDetailUiState> by lazy {
        MutableLiveData<ItemDetailUiState>(ItemDetailUiState())
    }

    private fun getItemFromDb() {
        viewModelScope.launch {
            _uiState.value?.let { currentUiState ->
                _uiState.value = currentUiState.copy(
                    item = getItemByIdUseCase.execute(_uiState.value?.itemId!!)
                )
            }
        }
    }

    fun setItemId(itemId: String) {
        _uiState.value?.let {currentUiState ->
            _uiState.value = currentUiState.copy(
                item = currentUiState.item,
                itemId = itemId
            )
        }
    }

    fun getItem() {
        getItemFromDb()
    }


    fun streamUiState(): LiveData<ItemDetailUiState> = _uiState

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val getItemByIdUseCase: GetItemByIdUseCase
        ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ItemDetailViewModel(getItemByIdUseCase) as T
        }
    }

}