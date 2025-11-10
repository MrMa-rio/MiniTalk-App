package com.marsn.minitalk.ui.feature.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.marsn.minitalk.R
import com.marsn.minitalk.navigation.InitialRoute
import com.marsn.minitalk.navigation.LoginRoute
import com.marsn.minitalk.navigation.RegisterRoute
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.theme.SairaSemiExpanded

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RegisterScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {

    // 🔹 Efeito só uma vez, sem reexecutar a cada navegação
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UIEvent.NavigateTo<*> -> {
                    when (uiEvent.route) {
                        is LoginRoute -> {
                            navController.navigate(LoginRoute) {
                                popUpTo(RegisterRoute) { inclusive = true }
                                launchSingleTop = true
                            }
                        }

                        is InitialRoute -> {
                            navController.navigate(InitialRoute) {
                                popUpTo(RegisterRoute) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    }
                }

                else -> Unit
            }
        }
    }

    // 🔹 Mantém layout estável, sem recriar o Brush nem o gradient
    val gradient = remember {
        Brush.linearGradient(
            listOf(
                Color(0xFF0FBFAD),
                Color.White,
                Color.White,
                Color(0xFF0D9488)
            )
        )
    }

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .consumeWindowInsets(paddingValues)
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            item { Spacer(modifier = Modifier.height(64.dp)) }

            item {
                Column(
                    modifier = Modifier
                        .width(800.dp)
                        .height(200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.ic_launcher_3_foreground),
                        contentDescription = "Logo MiniTalk",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            item {
                Text(
                    text = "Primeiro Acesso",
                    fontWeight = FontWeight.Bold,
                    fontFamily = SairaSemiExpanded,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
                )
            }

            item { Form() }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { viewModel.onEvent(LoginEvent.Login) },
                    modifier = Modifier.fillMaxWidth(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "CADASTRAR",
                        fontWeight = FontWeight.Medium,
                        fontFamily = SairaSemiExpanded
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.onEvent(LoginEvent.Login) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Sou Cadastrado!",
                        fontWeight = FontWeight.Normal,
                        fontFamily = SairaSemiExpanded
                    )
                }
            }
        }
    }
}


@Composable
private fun Form() {
    ItemFormInput(Icons.Default.Person, "Email ou Usuário")

    Spacer(modifier = Modifier.height(16.dp))

    ItemFormInput(Icons.Default.Lock, "CPF")
}

@Composable
private fun ItemFormInput(
    imageVector: ImageVector? = null,
    placeholder: String
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        onValueChange = {

        },
        placeholder = { Text(placeholder) },
        shape = Shapes().medium,
        trailingIcon = {
            if (imageVector != null) {
                Image(
                    imageVector = imageVector,
                    contentDescription = "Logo MiniTalk",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
    )
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun FormSignInPreview() {
    RegisterScreen(navController = NavController(LocalContext.current))
}