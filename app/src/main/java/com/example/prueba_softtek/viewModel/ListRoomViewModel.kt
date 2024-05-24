package com.example.prueba_softtek.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.getMoviesRoom.IGetMoviesRoomCase
import com.example.prueba_softtek.ui.list.ListState
import com.example.prueba_softtek.ui.listRoom.ListRoomUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListRoomViewModel(private val getMoviesCase: IGetMoviesRoomCase) :ViewModel() {

    private val _uiState = MutableStateFlow<ListRoomUiState>(ListRoomUiState.Nothing)
    val uiState: StateFlow<ListRoomUiState> = _uiState

    var state by mutableStateOf(ListState())

    fun getMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = ListRoomUiState.Loading
            getMoviesCase.execute()
                .collect { r ->
                    if (r.message!=null) _uiState.value = ListRoomUiState.Error(r.message!!)
                    else{
                        _uiState.value = ListRoomUiState.Success(r.data)
                    }
                }
        }
    }
    fun resetStateError() {
        state = state.copy(error = null,isLoading= false)
    }
}