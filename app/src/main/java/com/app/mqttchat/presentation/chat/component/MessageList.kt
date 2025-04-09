package com.app.mqttchat.presentation.chat.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.mqttchat.domain.model.ChatMessageModel
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.presentation.chat.isMine
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MessageList(
  modifier: Modifier = Modifier,
  messages: StateFlow<List<ChatMessageModel>>,
  me: UserModel?
) {
  val messageList by messages.collectAsStateWithLifecycle()
  LazyColumn(
    modifier = modifier.padding(horizontal = 8.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    items(messageList, key = { it.id }) { ChatBubble(it, isMine = isMine(it, me)) }
  }
}