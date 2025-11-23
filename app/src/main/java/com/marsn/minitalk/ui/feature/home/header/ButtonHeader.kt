package com.marsn.minitalk.ui.feature.home.header

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.ui.theme.ButtonColorsTransparents


@Composable
fun ButtonHeader(
    onClick: () -> Unit,
    contentDescription: String,
    tint: Color = Color.White,
    painterResource: Painter
) {
    Button(
        onClick = onClick,
        colors = ButtonColorsTransparents
    ) {
        Icon(
            painterResource,
            contentDescription = contentDescription,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp),
            tint = tint
        )
    }
}