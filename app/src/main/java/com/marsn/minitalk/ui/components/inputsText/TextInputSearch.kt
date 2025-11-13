package com.marsn.minitalk.ui.components.inputsText

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.R


@Composable
fun TextInputSearch() {


    var value by remember { mutableStateOf("") }

    InputField(
        value = value,
        onValueChange = { value = it },
        placeholder = "Pesquisar",
        imageVector = ImageVector.vectorResource(id = R.drawable.search),
        keyboardType = KeyboardType.Text,
        modifier = Modifier
            .fillMaxWidth(),
        colors = textInputColors(),
        contextDescription = "Search",
        singleLine = true
    )

}

@Composable
fun textInputColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,

    focusedTextColor = Color.Black,
    unfocusedTextColor = Color.White,
    disabledTextColor = Color.Gray,

    unfocusedPlaceholderColor = Color.Gray,

    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,

    cursorColor = Color.Black,
    focusedTrailingIconColor = Color.Gray,
    unfocusedTrailingIconColor = Color.Black
)