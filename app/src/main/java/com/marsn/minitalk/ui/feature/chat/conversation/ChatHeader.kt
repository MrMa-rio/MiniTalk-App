package com.marsn.minitalk.ui.feature.chat.conversation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marsn.minitalk.R
import com.marsn.minitalk.ui.feature.home.ConversationEvent
import com.marsn.minitalk.ui.theme.ButtonColorsTransparents


@Composable
fun ChatHeader( onEvent: (ConversationEvent) -> Unit) {

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


    Surface(
        modifier = Modifier,
        shadowElevation = shadowElevation,
        tonalElevation = tonalElevation,
        color = Color(0xFF0FBFAD),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0FBFAD))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        onEvent(ConversationEvent.Home)
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Voltar",
                        tint = Color.White
                    )

                }


                Button(
                    onClick = {},
                    colors = ButtonColorsTransparents,
                ) {
                    Icon(
                        modifier = Modifier
                            .clip(ShapeDefaults.ExtraLarge)
                            .background(Color.Transparent)
                            .width(46.dp)
                            .height(46.dp),
                        tint = Color.White,
                        imageVector = ImageVector.vectorResource(id = R.drawable.person),
                        contentDescription = "Perfil"

                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("MOCK", color = Color.White, fontSize = 18.sp)
                        Text("online agora", color = Color.White.copy(0.7f), fontSize = 14.sp)
                    }
                }
            }
            Row(
                modifier = Modifier.padding(horizontal = 6.dp),
            ) {
                IconButton(
                    onClick = {/*TODO*/ }
                ) {
                    Icon(
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp),
                        painter = painterResource(id = R.drawable.video),
                        contentDescription = "video-chamada",
                        tint = Color.White
                    )
                }
                Spacer(Modifier.width(18.dp))
                IconButton(
                    onClick = {/*TODO*/ }
                ) {
                    Icon(
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp),
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = "audio-chamada",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
