package com.app.mqttchat.presentation.chatlist.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.app.mqttchat.domain.model.ChatItem
import com.app.mqttchat.presentation.navigation.ScreenRoutes

@Composable
fun ChatList(
  navController: NavHostController
) {
  val chatLists by remember { mutableStateOf(listOf(ChatItem(roomId = "1", title = "Liz", subtitle = "Hey..."))) }

  LazyColumn(modifier = Modifier.fillMaxSize()) {
    items(chatLists) { item ->
      ChatListItem(item) {
        navController.navigate(ScreenRoutes.ChatRoute.navigate(it.roomId))
      }
    }
  }
}