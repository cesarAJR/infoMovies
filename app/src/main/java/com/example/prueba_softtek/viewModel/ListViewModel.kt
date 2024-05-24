package com.example.prueba_softtek.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.getMovies.IGetMoviesCase
import com.example.domain.useCase.logout.ILogoutUserCase
import com.example.prueba_softtek.ui.list.ListState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.example.domain.core.Result
import com.example.prueba_softtek.ui.list.ListUiState
import com.example.prueba_softtek.ui.login.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Movie

class ListViewModel(private val getMoviesCase: IGetMoviesCase,private val logoutUserCase: ILogoutUserCase) :ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<Movie>>(value = PagingData.empty())
    val uiState: StateFlow<PagingData<Movie>> = _uiState

    var state by mutableStateOf(ListState())

    fun getMovies(){
        Log.d("load---","calll")
        viewModelScope.launch(Dispatchers.IO) {
//            _uiState.value = ListUiState.Loading
            getMoviesCase.execute()
                .cachedIn(viewModelScope)
                .collect { r ->
//                    if (r.message!=null) _uiState.value = ListUiState.Error(r.message!!)
//                    else{
                    Log.d("load---","calll")
                        _uiState.value = r
//                    }
                }
        }
    }
    fun resetStateError() {
        state = state.copy(error = null,isLoading= false)
    }

    fun cleanList(){
        _uiState.value =  PagingData.empty()
    }

     fun resetLogin() {
         viewModelScope.launch {
             logoutUserCase.execute()
         }

    }
}