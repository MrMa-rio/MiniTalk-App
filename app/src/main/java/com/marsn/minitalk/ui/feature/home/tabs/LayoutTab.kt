package com.marsn.minitalk.ui.feature.home.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marsn.minitalk.ui.theme.SairaSemiExpanded


@Composable
fun LayoutTab( selectedTabIndex: Int, setSelectedTabIndex: (Int) -> Unit ) {



    Column {
        SecondaryTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent,
            divider = {},

            contentColor = Color.White,
            indicator = {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(selectedTabIndex),
                    height = 1.dp,
                    color = Color.Transparent
                )
            },
        ) {
            IndexTab.tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { setSelectedTabIndex(index) },
                    selectedContentColor = Color(0xFF1FBFAD),
                    unselectedContentColor = Color.White,
                    text = { Text(title, fontFamily = SairaSemiExpanded, fontSize = 11.sp) },
                    modifier = Modifier
                        .background(
                            color = if (selectedTabIndex == index) Color.White else Color.Transparent,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(shape = RoundedCornerShape(8.dp))
                )
            }
        }
    }
}