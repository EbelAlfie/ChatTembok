package com.app.mqttchat.data.repository

import com.app.mqttchat.data.api.RealtimeApiClient
import com.app.mqttchat.domain.model.MessageModel
import com.app.mqttchat.domain.repository.ChatRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
  private val realtimeApiClient: RealtimeApiClient
): ChatRepository {
  override fun sendMessage(chatRoomId: String, message: MessageModel) {
    val gson = Gson()
    val msg = gson.toJson(message)
    realtimeApiClient.send("chat/${chatRoomId}/send", msg)
  }

  override fun observeMessage(chatRoomId: String): Flow<MessageModel> {
    return realtimeApiClient.listen("chat/${chatRoomId}/events")
      .map {
        val gson = Gson()
        val messageModel = gson.fromJson(it, MessageModel::class.java)
        messageModel
      }
  }
}