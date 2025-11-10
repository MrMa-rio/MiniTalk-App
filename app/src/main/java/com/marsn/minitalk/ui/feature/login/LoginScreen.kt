package com.marsn.minitalk.ui.feature.login

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.marsn.minitalk.R
import com.marsn.minitalk.navigation.InitialRoute
import com.marsn.minitalk.navigation.LockScreenOrientation
import com.marsn.minitalk.navigation.LoginRoute
import com.marsn.minitalk.navigation.RegisterRoute
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(navController: NavController) {

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val viewModel = viewModel<LoginViewModel> { LoginViewModel() }

    // Evita recomposição pesada
    val uiEvent = remember { viewModel.uiEvent }

    // Coleta eventos de navegação
    LaunchedEffect(Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                is UIEvent.NavigateTo<*> -> when (event.route) {
                    is InitialRoute -> navController.navigate(InitialRoute) {
                        popUpTo(LoginRoute) { inclusive = true }
                        launchSingleTop = true
                    }

                    is RegisterRoute -> navController.navigate(RegisterRoute) {
                        popUpTo(LoginRoute) { inclusive = true }
                        launchSingleTop = true
                    }
                }

                else -> {}
            }
        }
    }

    LoginContent(onEvent = viewModel::onEvent)
}

@Composable
private fun LoginContent(onEvent: (LoginEvent) -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0FBFAD),
                            Color.White,
                            Color(0xFF0D9488)
                        )
                    )
                )
                .consumeWindowInsets(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(140.dp))

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

            // Inputs
            var username by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            InputField(
                value = username,
                onValueChange = { username = it },
                placeholder = "Nome de usuário",
                imageVector = Icons.Default.Person
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Senha",
                imageVector = Icons.Default.Lock,
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botões
            Button(
                onClick = { onEvent(LoginEvent.Logged(username)) },
                modifier = Modifier.fillMaxWidth(0.6f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "ACESSAR", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { onEvent(LoginEvent.Register) }) {
                Text("Primeiro Acesso", color = Color.Black)
            }
        }
    }
}

@Composable
private fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    imageVector: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        trailingIcon = {
            Icon(imageVector, contentDescription = null)
        },
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}