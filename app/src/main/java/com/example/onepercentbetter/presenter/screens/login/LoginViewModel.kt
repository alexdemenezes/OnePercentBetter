package com.example.onepercentbetter.presenter.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _uiState: MutableLiveData<LoginUiState> by lazy {
        MutableLiveData(LoginUiState())
    }

    val uiState: LiveData<LoginUiState>
        get() = _uiState

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun setUserData(email: String, password: String) {
        _uiState.value?.let { currentUiState ->
            _uiState.value = currentUiState.copy(
                email = email,
                password = password
            )
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(
                    _uiState.value?.email!!,
                    _uiState.value?.password!!
                )
                
                _uiState.value?.let { currentUiState ->
                    _uiState.value = currentUiState.copy(loggedIn = true)
                }
            } catch (e: Exception) {
                _uiState.value?.let { currentUiState ->
                    _uiState.value = currentUiState.copy(showFailureSnackbar = true)
                }
                Log.d("LoginViewModel", "login error: ${e.message}")
            }
        }
    }

}