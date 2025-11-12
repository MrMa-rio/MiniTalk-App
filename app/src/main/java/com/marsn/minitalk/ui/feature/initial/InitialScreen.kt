package com.marsn.minitalk.ui.feature.initial

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marsn.minitalk.R
import com.marsn.minitalk.ui.components.enums.AppDestinations
import com.marsn.minitalk.ui.theme.SairaSemiExpanded


@Composable
fun InitialScreen() {
    MiniTalkApp()
}


@PreviewScreenSizes
@Composable
fun MiniTalkApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.CHAT) }
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

    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {/*TODO*/ },
                modifier = Modifier.offset(y = (-32).dp, x = (-16).dp),
                containerColor = Color(0xFF1FBFAD)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.msg),
                    contentDescription = "Add",
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )
            }
        }) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(gradient)
                .consumeWindowInsets(it)
        ) {


            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box() {
                            Icon(
                                painterResource(id = R.drawable.person),
                                contentDescription = "Person",
                                modifier = Modifier
                                    .width(48.dp)
                                    .height(48.dp)
                            )
                        }
                        Text(
                            "MiniTalk",
                            fontFamily = SairaSemiExpanded,
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp
                        )
                        Box() {
                            Icon(
                                painterResource(id = R.drawable.settings),
                                contentDescription = "Settings",
                                modifier = Modifier
                                    .width(42.dp)
                                    .height(42.dp)
                            )
                        }
                    }
                    TabNavigationScreen(selectedTabIndex)
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .border(
                            0.dp,
                            Color.Transparent,
                            shape = RoundedCornerShape(42.dp)
                        )
                        .clip(RoundedCornerShape(42.dp))
                        .background(Color.White),
                ) {

                    when (selectedTabIndex) {
                        0 -> HomeScreen()
                        1 -> ProfileScreen()
                        2 -> SettingsScreen()
                    }
                }
            }

        }
    }

}

@Composable
fun SettingsScreen() {
    Text("HELLO WORLD!!")
}

@Composable
fun TesteScreen() {
    Text("HELLO WORLD!!!!!")
}

@Composable
fun TabNavigationScreen(selectedTabIndex: Int) {
    val tabs = listOf("Mensagens", "Grupos", "Chamadas", "Perfil")


    Column {
        // 🔹 Cabeçalho com as abas
        SecondaryTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent,
            divider = {},

            contentColor = Color.White,          // Cor do texto e indicador da aba selecionada
            indicator = {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(selectedTabIndex),
                    height = 1.dp
                )
            },
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { /*TODO*/ },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.White,
                ) {
                    val isSelected = selectedTabIndex == index

                    // 🔹 Container visual da Tab
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                            .background(
                                color = if (isSelected) Color.White else Color.Transparent,
                                shape = RoundedCornerShape(12.dp) // Borda semi arredondada
                            ).padding(vertical = 12.dp, horizontal = 8.dp)

                    ) {
                        Text(
                            text = title,
                            fontSize = 11.sp,
                            fontFamily = SairaSemiExpanded,
                            color = if (isSelected) Color.Black else Color.White
                        )
                    }
                }
            }
        }


        // 🔹 Conteúdo de cada aba

    }
}

@Composable
fun HomeScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("🏠 Tela Inicial")
    }
}

@Composable
fun ProfileScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("👤 Perfil do Usuário")
    }
}
