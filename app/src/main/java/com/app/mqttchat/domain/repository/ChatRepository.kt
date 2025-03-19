package com.app.mqttchat.domain.repository

import com.app.mqttchat.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
  fun sendMessage(chatRoomId: String, message: MessageModel)

  fun observeMessage(chatRoomId: String): Flow<MessageModel>
}