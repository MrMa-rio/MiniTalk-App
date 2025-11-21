package com.marsn.minitalk.ui.feature.home.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.domain.conversation.ConversationItem
import com.marsn.minitalk.core.usecase.formattedDateForChatLastMessage
import com.marsn.minitalk.core.usecase.formatterTimestamp
import com.marsn.minitalk.ui.feature.home.ConversationEvent
import com.marsn.minitalk.ui.theme.SairaSemiExpanded
import kotlinx.datetime.LocalDateTime
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@Composable
fun ConversationsTab(conversations: List<ConversationItem>, onEvent: (ConversationEvent) -> Unit) {
    val colors = remember {
        ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Black,
        )
    }

    LazyColumn {

        itemsIndexed(conversations.sortedBy { it.lastMessageTimestamp }) { index, item ->
            Button(
                onClick = {
                    onEvent(ConversationEvent.Chat(item.conversationId))
                }, colors = colors,
                shape = ShapeDefaults.ExtraSmall
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    modifier = Modifier
                                        .width(48.dp)
                                        .height(48.dp),
                                    contentDescription = "Person",
                                )
                            }

                            Column(
                                modifier = Modifier.padding(4.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item.participantName,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = SairaSemiExpanded
                                )
                                Text(
                                    text = item.lastMessage ?: "",
                                    maxLines = 1,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = SairaSemiExpanded
                                )
                            }
                        }
                        Column {
                            Text(
                                text = formatterTimestamp(item.lastMessageTimestamp?:0)
                                    .formattedDateForChatLastMessage(),
                                fontFamily = SairaSemiExpanded
                            )
                        }
                    }
                }
            }
        }
    }
}