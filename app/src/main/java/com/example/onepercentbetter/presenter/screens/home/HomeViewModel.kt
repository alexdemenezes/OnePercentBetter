package com.example.onepercentbetter.presenter.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.onepercentbetter.domain.usecase.item.GetItemsByTodayUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getItemsByTodayUseCase: GetItemsByTodayUseCase
): ViewModel() {


    private val _uiState: MutableLiveData<HomeUiState> by lazy {
        MutableLiveData<HomeUiState>(HomeUiState(itemList = emptyList()))
    }

    fun refreshItemList() {
        viewModelScope.launch {
            _uiState.value?.let { currentUiState ->
                _uiState.value = currentUiState.copy(
                    itemList = getItemsByTodayUseCase.execute()
                )
            }
        }
    }

    fun streamUiState(): LiveData<HomeUiState> = _uiState


    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val getItemsByTodayUseCase: GetItemsByTodayUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(getItemsByTodayUseCase) as T
        }
    }

}