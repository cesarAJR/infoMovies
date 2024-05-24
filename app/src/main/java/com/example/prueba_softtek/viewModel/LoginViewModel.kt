package com.example.prueba_softtek.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.login.ILoginUserCase
import com.example.prueba_softtek.ui.login.LoginElements
import com.example.prueba_softtek.ui.login.LoginState
import com.example.prueba_softtek.ui.login.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class LoginViewModel(private val userLoginUserCase: ILoginUserCase):ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Nothing)
    val uiState: StateFlow<LoginUiState> = _uiState


    var state by mutableStateOf(LoginState())
    var stateElements by mutableStateOf(LoginElements())

    fun login(){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = LoginUiState.Loading
            userLoginUserCase.execute(stateElements.user,stateElements.password)
                .collect { r ->
                    if (r.message!=null) _uiState.value = LoginUiState.Error(r.message!!)
                    else _uiState.value = LoginUiState.Success(r.data)
                }
        }
    }

//    fun login(user:String,password:String ){
//        viewModelScope.launch(Dispatchers.IO) {
//            state = state.copy( loading = true)
//            userLoginUserCase.execute(user,password).collect {
//                    state = if (it.data!=null){
//                        state.copy(successfull = "Bienvenido ${it.data!!.name}",loading = false)
//                    }else {
//                        state.copy(error = "Datos invalidos",loading = false)
//                    }
//                }
//            }
//        }


    fun changeUser(user:String){
        stateElements= stateElements.copy(user=user)
    }

    fun changePassword(password:String){
        stateElements= stateElements.copy(password = password)
    }

    fun resetStateUserError() {
        state = state.copy(successfull = null, error = null)
    }
}