package com.app.mqttchat.presentation.chat

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.mqttchat.presentation.chat.component.ChatFooter
import com.app.mqttchat.presentation.chat.component.ChatHeader
import com.app.mqttchat.presentation.chat.component.ChatList

@Composable
fun ChatRoomScreen() {
  Scaffold(
    topBar = {
      ChatHeader()
    },
    bottomBar = {
      ChatFooter()
    },
    content = {
      ChatList(modifier = Modifier.padding(it))
    }
  )
}