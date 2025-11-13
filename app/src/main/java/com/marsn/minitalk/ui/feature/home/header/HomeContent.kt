package com.marsn.minitalk.ui.feature.home.header

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.ui.components.inputsText.TextInputSearch
import com.marsn.minitalk.ui.feature.home.tabs.LayoutTab
import com.marsn.minitalk.ui.feature.home.tabs.ListChatTab


@Composable
fun HomeContent() {


    var selectedTabIndex by remember { mutableIntStateOf(0) }

    LazyColumn {

        item {
            Column(
                modifier = Modifier
                    .height(200.dp)
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabsHeader()
                LayoutTab(selectedTabIndex, { selectedTabIndex = it })
                TextInputSearch()
            }
        }

        item {
            Column(
                modifier = Modifier
                    .border(
                        0.dp,
                        Color.Transparent,
                        shape = RoundedCornerShape(42.dp, 42.dp, 0.dp, 0.dp)
                    )
                    .clip(RoundedCornerShape(42.dp, 42.dp, 0.dp, 0.dp))
                    .background(Color.White)

            ) {

                Column(
                    modifier = Modifier.fillParentMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    when (selectedTabIndex) {
                        0 -> ListChatTab()
                        2 -> ListChatTab()
                    }
                }

            }
        }
    }

}
