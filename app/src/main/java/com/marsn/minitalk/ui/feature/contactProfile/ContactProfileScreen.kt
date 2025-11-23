package com.marsn.minitalk.ui.feature.contactProfile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.marsn.minitalk.core.domain.UserProfile

@Composable
fun ContactProfileScreen(user: UserProfile) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(user.name)
    }
}

