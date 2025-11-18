package com.marsn.minitalk.ui.feature.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import com.marsn.minitalk.R
import com.marsn.minitalk.ui.components.inputsText.InputField
import com.marsn.minitalk.ui.feature.chat.contact.ContactEvent
import com.marsn.minitalk.ui.feature.home.ConversationEvent


@Composable
fun TextInputSearch(searchText: String, onEvent: (ConversationEvent) -> Unit) {

    InputField(
        value = searchText,
        onValueChange = { onEvent(ConversationEvent.SearchText(it)) },
        placeholder = "Pesquisar",
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.search),
                contentDescription = "Search",
            )
        },
        keyboardType = KeyboardType.Text,
        modifier = Modifier
            .fillMaxWidth(),
        colors = textInputColors(),
        singleLine = true,
        tintContent = Color(0xFF0FBFAD)
    )

}

@Composable
fun textInputColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color(0xFF0FBFAD),
    disabledContainerColor = Color.Transparent,

    focusedTextColor = Color.Black,
    unfocusedTextColor = Color.White,
    disabledTextColor = Color.Gray,

    unfocusedPlaceholderColor = Color.White,

    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,

    cursorColor = Color.Black,
    focusedTrailingIconColor = Color.Black,
    unfocusedTrailingIconColor = Color.White
)