package com.marsn.minitalk.ui.components.inputsText

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.ui.theme.SairaSemiExpanded
import com.marsn.minitalk.ui.theme.textInputColors

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    colors: TextFieldColors = textInputColors(),
    singleLine: Boolean = false,
    tintContent: Color = Color.White,
    trailingIcon: @Composable () -> Unit = {}
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val shadowElevation by animateDpAsState(
        targetValue = if (isFocused) 5.dp else 10.dp,
        label = "shadow_elevation"
    )
    val tonalElevation by animateDpAsState(
        targetValue = if (isFocused) 3.dp else 6.dp,
        label = "tonal_elevation"
    )

    val isTransparent = tintContent.alpha == 0f

    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp),
        shape = MaterialTheme.shapes.medium,
        shadowElevation = if (isTransparent) 0.dp else shadowElevation,
        tonalElevation = if (isTransparent) 0.dp else tonalElevation,
        color = tintContent,
    ) {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    fontFamily = SairaSemiExpanded,
                    modifier = modifier
                )
            },
            trailingIcon =  trailingIcon ,
            textStyle = TextStyle(fontFamily = SairaSemiExpanded),
            shape = MaterialTheme.shapes.medium,
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = colors,
            interactionSource = interactionSource
        )
    }
}