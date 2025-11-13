package com.marsn.minitalk.ui.feature.login

import android.content.pm.ActivityInfo
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marsn.minitalk.R
import com.marsn.minitalk.navigation.AuthRoutes
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.navigation.LockScreenOrientation
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.theme.SairaSemiExpanded
import com.marsn.minitalk.ui.theme.textInputColors
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen( onNavigateToRegister: () -> Unit, onNavigateToHome: () -> Unit) {

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val viewModel = viewModel<LoginViewModel> { LoginViewModel() }

    val uiEvent = remember { viewModel.uiEvent }

    LaunchedEffect(Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                is UIEvent.NavigateTo<*> -> when (event.route) {
                    is ChatRoutes.HomeRoute -> onNavigateToHome.invoke()
                    is AuthRoutes.RegisterRoute -> onNavigateToRegister.invoke()
                    else -> {}
                }

                else -> {}
            }
        }
    }

    LoginContent(onEvent = viewModel::onEvent)
}

@Composable
private fun LoginContent(onEvent: (LoginEvent) -> Unit) {

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

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
            verticalArrangement = Arrangement.Center
        ) {

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
                InputField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "Nome de usuário",
                    imageVector = Icons.Default.Person
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                InputField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Senha",
                    imageVector = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password
                )
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }

            item {
                Button(
                    onClick = { onEvent(LoginEvent.Logged(username)) },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "ACESSAR", fontWeight = FontWeight.Medium, fontFamily = SairaSemiExpanded)
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                TextButton(onClick = { onEvent(LoginEvent.Register) }) {
                    Text("Primeiro Acesso", color = Color.Black, fontFamily = SairaSemiExpanded)
                }
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
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, fontFamily = SairaSemiExpanded) },
        trailingIcon = {
            Icon(imageVector = imageVector, contentDescription = null, tint = Color.Black)
        },
        textStyle = TextStyle(fontFamily = SairaSemiExpanded),
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = textInputColors(),
    )


}