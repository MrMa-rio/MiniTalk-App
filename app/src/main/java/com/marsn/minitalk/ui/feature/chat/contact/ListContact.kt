package com.marsn.minitalk.ui.feature.chat.contact

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.ui.components.imageProfile.ImageProfile
import com.marsn.minitalk.ui.theme.SairaSemiExpanded


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListContact(contacts: LazyPagingItems<Contact>, onEvent: (ContactEvent) -> Unit) {

//    val listContact = contacts?.collectAsState(initial = emptyList())

    val colors = remember {
        ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Black,
        )
    }
//    LazyColumn {
//        itemsIndexed(listContact?.value?.sortedBy { it.name } ?: listOf()) { index, item ->
//
//            Button(
//                onClick = { onEvent(ContactEvent.SelectContact(item)) }
//                , colors = colors,
//                shape = ShapeDefaults.ExtraSmall
//            ) {
//                Box(
//                    Modifier
//                        .fillMaxWidth()
//                        .height(80.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Row(
//                            horizontalArrangement = Arrangement.SpaceAround,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            ImageProfile(item.avatarUrl, RoundedCornerShape(48.dp))
//                            Spacer(modifier = Modifier.width(6.dp))
//                            Column(
//                                modifier = Modifier.padding(4.dp),
//                                verticalArrangement = Arrangement.SpaceBetween
//                            ) {
//                                Text(
//                                    text = item.name,
//                                    fontWeight = FontWeight.Medium,
//                                    fontFamily = SairaSemiExpanded
//                                )
//                                Text(
//                                    text = item.email,
//                                    maxLines = 1,
//                                    color = Color.Gray,
//                                    fontWeight = FontWeight.Light,
//                                    fontFamily = SairaSemiExpanded
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
    LazyColumn {
        items(contacts.itemCount) { index ->
            contacts[index]?.let { contact ->
                Button(
                onClick = { onEvent(ContactEvent.SelectContact(contact)) }
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
                            ImageProfile(contact.avatarUrl, RoundedCornerShape(48.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Column(
                                modifier = Modifier.padding(4.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = contact.name,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = SairaSemiExpanded
                                )
                                Text(
                                    text = contact.email,
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

        contacts.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingShimmer() }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingMoreItem() }
                }
                loadState.append is LoadState.Error -> {
                    item { RetryButton(onClick = { retry() }) }
                }
            }
        }
    }
}

@Composable
fun LoadingShimmer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // Avatar
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.4f))
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Linha 1
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(14.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray.copy(alpha = 0.4f))
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Linha 2
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(14.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray.copy(alpha = 0.3f))
        )
    }
}

@Composable
fun LoadingMoreItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.CircularProgressIndicator()
        Spacer(modifier = Modifier.height(8.dp))
        Text("Carregando mais...", color = Color.Gray)
    }
}

@Composable
fun RetryButton(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ocorreu um erro ao carregar.",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))

        androidx.compose.material3.Button(onClick = onClick) {
            Text("Tentar novamente")
        }
    }
}
