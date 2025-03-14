package com.app.mqttchat.domain.repository

import kotlinx.coroutines.flow.Flow

interface ChatRepository {
  fun sendMessage(chatRoomId: String, message: String)

  fun observeMessage(chatRoomId: String): Flow<String>
}