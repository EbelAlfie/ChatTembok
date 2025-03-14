package com.app.mqttchat.data.repository

import com.app.mqttchat.data.api.RealtimeApiClient
import com.app.mqttchat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
  private val realtimeApiClient: RealtimeApiClient
): ChatRepository {
  override fun sendMessage(chatRoomId: String, message: String) {
    realtimeApiClient.send("chat/${chatRoomId}/send", message)
  }

  override fun observeMessage(chatRoomId: String): Flow<String> {
    return realtimeApiClient.listen("chat/${chatRoomId}/events")
  }
}