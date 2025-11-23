package com.marsn.minitalk.ui.components.overflowMenu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OverflowMenu(onRefresh: () -> Unit) {


    var expanded by remember { mutableStateOf(false) } // controla se o menu está aberto

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert, // ícone de 3 pontos verticais
                contentDescription = "Menu",
                tint = Color.White
            )
        }

        DropdownMenu (
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                onRefresh()
                expanded = false
            }, text = {
                Text("Atualizar")
            }, modifier = Modifier.height(28.dp) )
        }
    }
}
