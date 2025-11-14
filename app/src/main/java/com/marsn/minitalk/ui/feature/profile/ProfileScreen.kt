package com.marsn.minitalk.ui.feature.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.marsn.minitalk.model.UserProfile

@Composable
fun ProfileScreen(user: UserProfile) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(user.name)
    }
}

