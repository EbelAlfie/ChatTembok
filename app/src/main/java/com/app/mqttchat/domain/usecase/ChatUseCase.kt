package com.app.mqttchat.domain.usecase

import com.app.mqttchat.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface ChatUseCase {
  fun sendMessage(chatRoomId: String, message: MessageModel)

  fun observeMessage(chatRoomId: String): Flow<MessageModel>
}