package com.marsn.minitalk.ui.components.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MessageBubbleOwn(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier
                .background(
                    color = Color(0xFFD0F8C6), // verde claro estilo WhatsApp
                    shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp)
                )
                .padding(12.dp)
        )
    }
}


