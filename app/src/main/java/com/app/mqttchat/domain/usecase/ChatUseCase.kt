package com.app.mqttchat.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ChatUseCase {
  fun sendMessage(chatRoomId: String, message: String)

  fun observeMessage(chatRoomId: String): Flow<String>
}