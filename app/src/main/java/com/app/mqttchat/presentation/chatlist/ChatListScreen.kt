package com.app.mqttchat.presentation.chatlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.app.mqttchat.presentation.chatlist.component.ChatList

@Composable
fun ChatListScreen(
  navController: NavHostController,
) {
  ChatList(navController)
}