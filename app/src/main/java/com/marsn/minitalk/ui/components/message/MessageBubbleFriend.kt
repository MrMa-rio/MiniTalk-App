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
import com.marsn.minitalk.model.Message
import com.marsn.minitalk.model.MessageText

@Composable
fun MessageBubbleFriend(message: MessageText) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = message.text,
            color = Color.White,
            modifier = Modifier
                .background(
                    color = Color(0x55000000),
                    shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp)
                )
                .padding(12.dp)
        )
    }
}