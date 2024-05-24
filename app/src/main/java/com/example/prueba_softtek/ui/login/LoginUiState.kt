package com.example.prueba_softtek.ui.login

import com.example.domain.model.User

sealed class LoginUiState {
    data class Success(val user: User?): LoginUiState()
    data class Error(val message: String): LoginUiState()
    object Loading: LoginUiState()
    object Nothing: LoginUiState()

}