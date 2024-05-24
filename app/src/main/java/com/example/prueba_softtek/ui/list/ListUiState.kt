package com.example.prueba_softtek.ui.list

import com.example.domain.model.Movie
import com.example.domain.model.User

sealed class ListUiState {
    data class Success(val list: List<Movie>?): ListUiState()
    data class Error(val message: String): ListUiState()
    object Loading: ListUiState()
    object Nothing: ListUiState()

}