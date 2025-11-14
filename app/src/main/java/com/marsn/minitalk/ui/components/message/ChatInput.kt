package com.marsn.minitalk.ui.components.message

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.R
import com.marsn.minitalk.ui.components.inputsText.InputField

@Composable
fun ChatInput() {
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.2f))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        InputField(
            value = message,
            onValueChange = { message = it },
            placeholder = "Mensagem...",
            keyboardType = KeyboardType.Text,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    0.dp,
                    Color.Transparent,
                    shape = RoundedCornerShape(42.dp)
                )
                .clip(RoundedCornerShape(42.dp)),
            colors = textInputColors(),
            tintContent = Color.Transparent,
            trailingIcon = {
                IconButton(
                    onClick = {
                        /*TODO*/
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0xFF0FBFAD),
                        contentColor = Color.White,
                    )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.send),
                        contentDescription = "Mensagem"
                    )
                }
            }
        )

        Spacer(Modifier.width(12.dp))
    }
}

@Composable
fun textInputColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White.copy(alpha = 0.3f),
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
