package com.marsn.minitalk.ui.feature.login

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.marsn.minitalk.R
import com.marsn.minitalk.navigation.InitialRoute
import com.marsn.minitalk.ui.UIEvent


@Composable
fun LoginScreen(navController: NavController) {

    val viewModel = viewModel<LoginViewModel> {
        LoginViewModel()
    }

    LaunchedEffect(Unit) {

        viewModel.uiEvent.collect { uiEvent ->

            when (uiEvent) {
                UIEvent.NavigateBack -> {}
                is UIEvent.NavigateTo<*> -> {
                    when (uiEvent.route) {
                        is InitialRoute -> {
                            navController.navigate(InitialRoute)
                        }
                    }
                }
                is UIEvent.ShowSnackbar -> {}
            }

        }

    }

    FormLogin(viewModel::onEvent)
}

@Composable
fun FormLogin(onEvent: (LoginEvent) -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF0FBFAD),
                            Color.White,
                            Color.White, // Cor inicial (verde-água MiniTalk)
                            Color(0xFF0D9488),   // Tom um pouco mais escuro
                            // Efeito de transição suave
                        )
                    )
                )
                .consumeWindowInsets(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            Spacer(modifier = Modifier.height(196.dp))
            Column(
                modifier = Modifier
                    .width(800.dp)
                    .height(200.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher_3_foreground),
                    contentDescription = "Logo MiniTalk",
                    modifier = Modifier.fillMaxSize(),

                    )
            }

            Form()

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onEvent(LoginEvent.Logged("Mario")) },
                modifier = Modifier.fillMaxWidth(0.5f),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
            ) {
                Text(text = "ACESSAR", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /*TODO*/ }, colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    )
            ) {
                Text(text = "Primeiro Acesso", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun Form() {
    ItemFormInput(Icons.Default.Person, "Nome de usuário")

    Spacer(modifier = Modifier.height(16.dp))

    ItemFormInput(Icons.Default.Lock, "Senha")
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