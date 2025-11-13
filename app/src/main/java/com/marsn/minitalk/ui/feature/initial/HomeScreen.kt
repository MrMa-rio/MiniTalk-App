package com.marsn.minitalk.ui.feature.initial

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marsn.minitalk.R
import com.marsn.minitalk.ui.components.inputsText.TextInputSearch
import com.marsn.minitalk.ui.theme.SairaSemiExpanded


@OptIn(ExperimentalMaterial3Api::class)
@PreviewScreenSizes
@Composable
fun HomeScreen() {
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

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val focusManager = LocalFocusManager.current

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
                    tint = Color.White,
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )
            }
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(gradient)
                .consumeWindowInsets(it)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        ) {

            HomeContentHeader(selectedTabIndex, setSelectedTabIndex = { index ->
                selectedTabIndex = index
            })
            HomeContentTab(selectedTabIndex)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeContentTab(
    selectedTabIndex: Int
) {

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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (selectedTabIndex) {
                0 -> InitialScreen()
                1 -> ProfileScreen()
                2 -> InitialScreen()
            }
        }

    }
}

@Composable
private fun HomeContentHeader(selectedTabIndex: Int, setSelectedTabIndex: (value: Int) -> Unit) {

    Column(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabHeader()
        TabsNavigation(selectedTabIndex, setSelectedTabIndex = setSelectedTabIndex)
        TextInputSearch()
    }

}

@Composable
fun TabHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonHeader(
            onClick = {/*TODO*/ },
            contentDescription = "Person",
            tint = Color.White,
            painterResource = painterResource(id = R.drawable.person)
        )
        Text(
            "MiniTalk",
            fontFamily = SairaSemiExpanded,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            color = Color.White
        )
        ButtonHeader(
            onClick = {/*TODO*/ },
            contentDescription = "Settings",
            tint = Color.White,
            painterResource = painterResource(id = R.drawable.settings)
        )
    }
}

@Composable
private fun ButtonHeader(
    onClick: () -> Unit,
    contentDescription: String,
    tint: Color = Color.White,
    painterResource: Painter
) {
    Button(
        onClick = onClick,
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.White
        )
    ) {
        Icon(
            painterResource,
            contentDescription = contentDescription,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp),
            tint = tint
        )
    }
}

@Composable
fun TabsNavigation(selectedTabIndex: Int, setSelectedTabIndex: (value: Int) -> Unit) {
    val tabs = listOf("Mensagens", "Grupos", "Chamadas", "Perfil")

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
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { setSelectedTabIndex(index) },
                    selectedContentColor = Color(0xFF1FBFAD),
                    unselectedContentColor = Color.White,
                    text = { Text(title, fontFamily = SairaSemiExpanded, fontSize = 11.sp) },
                    modifier = Modifier
                        .background(
                            color = if (selectedTabIndex == index) Color.White else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(shape = RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@Composable
fun InitialScreen() {



    LazyColumn {
        items(100) {
            Button(
                onClick = {

                }, colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Black,
                ),
                shape = ShapeDefaults.ExtraSmall
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(8.dp),
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
                            Icon(
                                imageVector = Icons.Default.Person,
                                modifier = Modifier
                                    .width(48.dp)
                                    .height(48.dp),
                                contentDescription = "Person",
                            )

                            Column(
                                modifier = Modifier.padding(8.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Nome")
                                Text(text = "Mensagem")
                            }
                        }
                        Column {
                            Text(text = "12:00")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("👤 Perfil do Usuário")
    }
}


