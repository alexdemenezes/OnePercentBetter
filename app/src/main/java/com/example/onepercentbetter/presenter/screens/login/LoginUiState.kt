package com.example.onepercentbetter.presenter.screens.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val loggedIn: Boolean = false,
    val showFailureSnackbar: Boolean = false
)
