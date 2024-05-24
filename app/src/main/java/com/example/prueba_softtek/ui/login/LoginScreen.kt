@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.prueba_softtek.ui.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba_softtek.R
import com.example.prueba_softtek.component.DialogLoading
import com.example.prueba_softtek.viewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel(), onLogin:()->Unit) {

    val stateElements = viewModel.stateElements
    val context = LocalContext.current
        var user by remember {
            mutableStateOf("")
        }

        var password by remember {
            mutableStateOf("")
        }

    var loading by remember {
        mutableStateOf<Boolean>(false)
    }


        val interactionSourceUser = remember { MutableInteractionSource() }
        val isFocusedUser by interactionSourceUser.collectIsFocusedAsState()

        val interactionSourcePassword = remember { MutableInteractionSource() }
        val isFocusedPassword by interactionSourcePassword.collectIsFocusedAsState()

        val IndicatorUnfocusedWidth = 1.dp
        val IndicatorFocusedWidth = 3.dp
        val TextFieldPadding = 16.dp

        val indicatorColor = Color.White
        val indicatorWidthUser = if (isFocusedUser) IndicatorFocusedWidth else IndicatorUnfocusedWidth
        val fontSizeLabelUser = if (isFocusedUser || user.isNotEmpty()) 12.sp else 18.sp
        val indicatorWidthPassword = if (isFocusedPassword) IndicatorFocusedWidth else IndicatorUnfocusedWidth
        val fontSizeLabelPassword = if (isFocusedPassword || password.isNotEmpty()) 12.sp else 18.sp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.back_login),
                contentScale = ContentScale.FillHeight
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "potencie"
        )
        Text(
            text = "Bienvenido",
            fontSize = 40.sp,
            style = TextStyle(color = Color.White),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(35.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .drawBehind {
                    val strokeWidth = indicatorWidthUser.value * density
                    val y = (size.height - strokeWidth / 2) - 15
                    drawLine(
                        indicatorColor,
                        Offset(TextFieldPadding.toPx(), y),
                        Offset(size.width - TextFieldPadding.toPx(), y),
                        strokeWidth
                    )
                }
            ,
            value = stateElements.user,
            onValueChange = {
                viewModel.changeUser(it)
            },
            enabled = true,
            interactionSource = interactionSourceUser,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor =  Transparent,
                unfocusedIndicatorColor = Transparent,
                disabledIndicatorColor = Transparent
            ),
            label = {
                Text(
                    text = "Usuario",
                    color = Color.White,
                    fontSize = fontSizeLabelUser
                )
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(40.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .drawBehind {
                    val strokeWidth = indicatorWidthPassword.value * density
                    val y = (size.height - strokeWidth / 2) - 15
                    drawLine(
                        indicatorColor,
                        Offset(TextFieldPadding.toPx(), y),
                        Offset(size.width - TextFieldPadding.toPx(), y),
                        strokeWidth
                    )
                }
            ,
            value = stateElements.password,
            onValueChange = {
                viewModel.changePassword(it)
            },
            enabled = true,
            interactionSource = interactionSourcePassword,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp
            ),
            visualTransformation =  PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor =  Transparent,
                unfocusedIndicatorColor = Transparent,
                disabledIndicatorColor = Transparent
            ),
            label = {
                Text(
                    "Contraseña",
                    color = Color.White,
                    fontSize = fontSizeLabelPassword
                )
            },
            trailingIcon = {

            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(horizontal = 30.dp),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(android.graphics.Color.parseColor("#0072CF"))
            ),
            onClick = {
                viewModel.login()
            }
        ) {
            Text(
                text = "Ingresar",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                )
            )
        }

        if (loading){
            DialogLoading(true)
        }else{
            DialogLoading(false)
        }

        LaunchedEffect(Unit){
           viewModel.uiState.collect{
               when(it){
                   is LoginUiState.Error -> {
                       Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                       loading = false
                   }
                   is LoginUiState.Loading -> {
                       loading = true
                   }
                   is LoginUiState.Success -> {
                       loading = false
                       Toast.makeText(context,it.user?.name?:"",Toast.LENGTH_SHORT).show()
                        onLogin()
                   }
                   is LoginUiState.Nothing -> {

                   }
               }
           }
        }
        viewModel.resetStateUserError()
    }
}

