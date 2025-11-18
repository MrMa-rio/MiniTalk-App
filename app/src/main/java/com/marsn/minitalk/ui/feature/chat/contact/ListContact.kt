package com.marsn.minitalk.ui.feature.chat.contact

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marsn.minitalk.R
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.ui.theme.SairaSemiExpanded
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListContact(contacts: Flow<List<Contact>>?, onEvent: (ContactEvent) -> Unit) {

    val listContact = contacts?.collectAsState(initial = emptyList())

    val colors = remember {
        ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Black,
        )
    }
    LazyColumn {
        itemsIndexed(listContact?.value?.sortedBy { it.name } ?: listOf()) { index, item ->

            Button(
                onClick = { onEvent(ContactEvent.SelectContact(item)) }
                , colors = colors,
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
                            AsyncImage(
                                model = item.profilePicture,
                                modifier = Modifier
                                    .width(48.dp)
                                    .height(48.dp)
                                    .clip(shape = ShapeDefaults.Medium),
                                contentDescription = "Person",
                                fallback = painterResource(R.drawable.person),
                                error = painterResource(R.drawable.person)
                            )
                            Spacer(modifier = Modifier.width(6.dp))

                            Column(
                                modifier = Modifier.padding(4.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item.name,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = SairaSemiExpanded
                                )
                                Text(
                                    text = item.email,
                                    maxLines = 1,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = SairaSemiExpanded
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}