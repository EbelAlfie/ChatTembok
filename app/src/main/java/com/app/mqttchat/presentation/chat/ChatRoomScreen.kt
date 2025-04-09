package com.app.mqttchat.presentation.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.mqttchat.presentation.chat.ChatViewModel.Companion.ChatViewModelFactory
import com.app.mqttchat.presentation.chat.component.ChatFooter
import com.app.mqttchat.presentation.chat.component.ChatHeader
import com.app.mqttchat.presentation.chat.component.MessageList
import com.app.mqttchat.presentation.ui.theme.Background

@Composable
fun ChatRoomScreen(
  navController: NavHostController,
  roomId: String,
  viewModel: ChatViewModel = hiltViewModel<ChatViewModel, ChatViewModelFactory> { it.create(roomId = roomId) },
) {

  LaunchedEffect(Unit) { viewModel.observeMessages() }

  Scaffold(
    containerColor = Background,
    topBar = {
      ChatHeader(
        onBackPressed = navController::popBackStack
      )
    },
    bottomBar = {
      ChatFooter(
        onSend = viewModel::sendMessage
      )
    },
    content = {
      MessageList(
        modifier = Modifier.fillMaxSize().padding(it),
        messages = viewModel.chatState,
        me = remember { viewModel.currentUser }
      )
    }
  )
}