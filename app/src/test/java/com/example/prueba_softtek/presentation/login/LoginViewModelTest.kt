package com.example.prueba_softtek.presentation.login

import com.example.domain.model.User
import com.example.domain.useCase.login.LoginUserCase
import com.example.prueba_softtek.viewModel.LoginViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest


class LoginViewModelTest : KoinTest{

    @MockK
    lateinit var repositoryImp: LoginUserCase


    @RelaxedMockK
    private lateinit var userData: User

    private lateinit var viewmodel : LoginViewModel

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewmodel =  LoginViewModel(repositoryImp)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `loging success`()= runTest {
//        val user = "Admin"
//        val password = "Password*123"
//
//        val flow = flow{
//            emit(Result.Successfull(userData))
//        }
//        coEvery { repositoryImp.login("Admin","Password*123")} returns flow
//       viewmodel.login(user,password)
//       assert(viewmodel.state.successfull!!.isNotEmpty())
    }

}