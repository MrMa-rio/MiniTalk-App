package com.marsn.minitalk.ui.feature.home.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marsn.minitalk.R
import com.marsn.minitalk.core.domain.UserProfile
import com.marsn.minitalk.ui.feature.home.ConversationEvent
import com.marsn.minitalk.ui.theme.SairaSemiExpanded


@Composable
fun TabsHeader(onEvent: (ConversationEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            "MiniTalk",
            fontFamily = SairaSemiExpanded,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            color = Color.White
        )

        ButtonHeader(
                onClick = {onEvent(ConversationEvent.Profile(UserProfile(
                    "Mario Alberto",
                    "teste@teste",
                    "16992283546"
                ))) },
        contentDescription = "Person",
        tint = Color.White,
        painterResource = painterResource(id = R.drawable.person)
        )
    }
}