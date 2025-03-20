package com.app.mqttchat.data.repository

import com.app.mqttchat.domain.model.ChatMessageModel
import com.app.mqttchat.domain.repository.ChatRepository
import com.app.realtime.api.RealtimeApiClient
import com.app.realtime.model.PublishRequest
import com.app.realtime.model.SubscribeRequest
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
  private val realtimeApiClient: RealtimeApiClient
) : ChatRepository {
  override fun sendMessage(chatRoomId: String, message: ChatMessageModel) {
    val gson = Gson()
    val msg = gson.toJson(message)
    realtimeApiClient.publish(
      PublishRequest.defaultPubRequest("chat/${chatRoomId}/send", msg)
    )
  }

  override fun observeMessage(chatRoomId: String): Flow<ChatMessageModel> {
    return realtimeApiClient.subscribe(
      SubscribeRequest.defaultSubRequest("chat/${chatRoomId}/events")
    )
      .map {
        val gson = Gson()
        val chatMessageModel = gson.fromJson(it, ChatMessageModel::class.java)
        chatMessageModel
      }
  }
}