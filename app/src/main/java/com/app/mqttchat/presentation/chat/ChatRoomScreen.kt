package com.app.mqttchat.presentation.chat

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.mqttchat.presentation.chat.component.ChatFooter
import com.app.mqttchat.presentation.chat.component.ChatHeader
import com.app.mqttchat.presentation.chat.component.ChatList

@Composable
fun ChatRoomScreen(
  viewModel: ChatViewModel = hiltViewModel(),
  navController: NavHostController
) {
  Scaffold(
    topBar = {
      ChatHeader()
    },
    bottomBar = {
      ChatFooter(
        onSend = viewModel::sendMessage
      )
    },
    content = {
      ChatList(modifier = Modifier.padding(it))
    }
  )
}