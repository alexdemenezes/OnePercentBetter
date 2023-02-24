package com.example.onepercentbetter.presenter.screens.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel(){
     private val _uiState: MutableLiveData<RegisterUiState> by lazy {
         MutableLiveData(RegisterUiState())
     }

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun setUserData(
        fullName: String,
        email: String,
        password: String
    )  {
       _uiState.value?.let { currentUiState ->
           _uiState.value = currentUiState.copy(
               fullName = fullName,
               email = email,
               password = password
           )
       }
    }

    fun registerUser() {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(_uiState.value?.email!!, _uiState.value?.password!!)
            } catch(e: Exception) {
                Log.d("RegisterViewModel", "erro: ${e.message}")
            }
        }
    }
}