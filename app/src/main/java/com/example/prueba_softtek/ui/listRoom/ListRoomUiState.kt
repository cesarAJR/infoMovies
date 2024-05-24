package com.example.prueba_softtek.ui.listRoom

import com.example.domain.model.Movie
import com.example.domain.model.User

sealed class ListRoomUiState {
    data class Success(val list: List<Movie>?): ListRoomUiState()
    data class Error(val message: String): ListRoomUiState()
    object Loading: ListRoomUiState()
    object Nothing: ListRoomUiState()

}