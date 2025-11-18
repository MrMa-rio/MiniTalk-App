package com.marsn.minitalk.ui.feature.chat.contact.header

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.ui.feature.home.TextInputSearch
import com.marsn.minitalk.ui.feature.chat.contact.ContactsViewModel
import com.marsn.minitalk.ui.feature.chat.contact.ListContact
import kotlinx.coroutines.flow.Flow


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContactContent(contacts: Flow<List<Contact>>, viewModel: ContactsViewModel) {


    val searchText by viewModel.searchText.collectAsState()

    Column {
        Column(
            modifier = Modifier
                .height(200.dp)
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TabsHeader()
            TextInputSearch(searchText = searchText) { viewModel::onEvent }
        }

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
            ) {

                ListContact(contacts, viewModel::onEvent)
            }

        }
    }

}
