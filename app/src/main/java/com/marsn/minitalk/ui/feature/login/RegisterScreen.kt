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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
import com.marsn.minitalk.ui.theme.textInputColors

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RegisterScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {

    // 🔹 Efeito só uma vez, sem reexecutar a cada navegação
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UIEvent.NavigateTo<*> -> {
                    when (uiEvent.route) {
                        is LoginRoute -> navController.navigate(LoginRoute) {
                            popUpTo(0) { inclusive = true } // limpa TODA a pilha
                            launchSingleTop = true          // evita duplicar o login
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

            item { Spacer(modifier = Modifier.height(140.dp)) }

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


    // Inputs
    var email by rememberSaveable { mutableStateOf("") }
    var cpf by rememberSaveable { mutableStateOf("") }

    InputField(
        value = email,
        onValueChange = { email = it },
        placeholder = "Email ou Usuário",
        imageVector = Icons.Default.Email,
        keyboardType = KeyboardType.Password
    )

    Spacer(modifier = Modifier.height(16.dp))

    InputField(
        value = cpf,
        onValueChange = { cpf = it },
        placeholder = "CPF",
        imageVector = Icons.Default.VerifiedUser,
        keyboardType = KeyboardType.Password
    )
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
        placeholder = { Text(placeholder, fontFamily = SairaSemiExpanded) },
        trailingIcon = {
            Icon(imageVector, contentDescription = null, tint = Color.Black)
        },
        textStyle = TextStyle(fontFamily = SairaSemiExpanded),
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = textInputColors()
    )
}
