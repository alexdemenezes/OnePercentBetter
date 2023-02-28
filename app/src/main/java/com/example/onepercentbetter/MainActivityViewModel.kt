package com.example.onepercentbetter

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivityViewModel: ViewModel() {
    private var _loggedIn: MutableLiveData<Boolean?> = MutableLiveData(null)
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    val loggedIn: LiveData<Boolean?>
        get() = _loggedIn

    fun checkLoggedInState() {
//        auth.signOut()
            viewModelScope.launch {
                delay(1000)
                _loggedIn.value = auth.currentUser != null
            }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainActivityViewModel() as T
        }
    }

}