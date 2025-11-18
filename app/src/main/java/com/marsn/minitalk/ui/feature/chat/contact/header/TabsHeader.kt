package com.marsn.minitalk.ui.feature.chat.contact.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.marsn.minitalk.ui.feature.chat.contact.ContactEvent
import com.marsn.minitalk.ui.feature.home.ConversationEvent
import com.marsn.minitalk.ui.theme.SairaSemiExpanded


@Composable
fun TabsHeader(onEvent: (ContactEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                onEvent(ContactEvent.NavigateBack)
            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "Voltar",
                tint = Color.White
            )

        }

        Text(
            "Contatos",
            fontFamily = SairaSemiExpanded,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            color = Color.White
        )



    }
}