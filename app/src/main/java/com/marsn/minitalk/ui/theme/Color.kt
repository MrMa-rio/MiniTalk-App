package com.marsn.minitalk.ui.theme

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val Blue40 = Color(0xFFC9EDED)

@Composable
fun textInputColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,        // fundo branco quando focado
    unfocusedContainerColor = Color.White,      // fundo branco quando não focado
    disabledContainerColor = Color.White,       // fundo branco mesmo se desativado

    focusedTextColor = Color.Black,             // texto preto quando focado
    unfocusedTextColor = Color.Black,           // texto preto quando não focado
    disabledTextColor = Color.Gray,             // texto cinza se desativado

    focusedPlaceholderColor = Color.Gray,       // placeholder cinza
    unfocusedPlaceholderColor = Color.Gray,

    focusedIndicatorColor = Color.Gray,        // borda preta quando focado
//    unfocusedIndicatorColor = Color.Gray,      // borda preta quando não focado
//    disabledIndicatorColor = Color.Gray,        // borda cinza quando desativado

    cursorColor = Color.Black,                  // cursor preto
    focusedTrailingIconColor = Color.Black,     // ícone preto quando focado
    unfocusedTrailingIconColor = Color.Black
)