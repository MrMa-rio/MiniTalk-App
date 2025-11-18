package com.marsn.minitalk.ui.components.imageProfile

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marsn.minitalk.R

@Composable
fun ImageProfile(urlAvatar: String, shapes: RoundedCornerShape) {
    AsyncImage(
        model = urlAvatar,
        modifier = Modifier
            .width(48.dp)
            .height(48.dp)
            .clip(shape = shapes),
        contentDescription = "Person",
        fallback = painterResource(R.drawable.person),
        error = painterResource(R.drawable.person)
    )
}