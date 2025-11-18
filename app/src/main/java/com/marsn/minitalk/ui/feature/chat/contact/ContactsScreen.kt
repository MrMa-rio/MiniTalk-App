package com.marsn.minitalk.ui.feature.chat.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.marsn.minitalk.ui.feature.chat.contact.header.ContactContent
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = koinViewModel<ContactsViewModel>(),
) {
    val gradient = remember {
        Brush.linearGradient(
            listOf(
                Color(0xFF0FBFAD),
                Color(0xFF1FBFAD),
                Color(0xFF2FBFAD),
                Color(0xFF3FBFAD)
            )
        )
    }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .background(gradient)
                .consumeWindowInsets(it)
        ) {
            ContactContent(viewModel)
        }
    }
}
