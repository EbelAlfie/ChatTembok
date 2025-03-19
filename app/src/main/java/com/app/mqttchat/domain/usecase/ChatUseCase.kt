package com.app.mqttchat.domain.usecase

import com.app.mqttchat.domain.model.ChatMessageModel
import kotlinx.coroutines.flow.Flow

interface ChatUseCase {
  fun sendMessage(chatRoomId: String, message: ChatMessageModel)

  fun observeMessage(chatRoomId: String): Flow<ChatMessageModel>
}