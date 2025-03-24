package com.app.mqttchat.data.repository

import com.app.mqttchat.data.model.RealtimeMessageEvent
import com.app.mqttchat.domain.model.ChatMessageModel
import com.app.mqttchat.domain.repository.ChatRepository
import com.app.realtime.RealtimeClient
import com.app.realtime.model.PublishRequest
import com.app.realtime.model.SubscribeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
  private val realtimeApiClient: RealtimeClient
) : ChatRepository {
  override fun sendMessage(chatRoomId: String, message: RealtimeMessageEvent) {
    realtimeApiClient.publishMessage(
      PublishRequest.defaultPubRequest("chat/${chatRoomId}/send", message),
      RealtimeMessageEvent::class.java
    )
  }

  override fun observeMessage(chatRoomId: String): Flow<ChatMessageModel> {
    return realtimeApiClient.subscribeMessage(SubscribeRequest.defaultSubRequest("chat/${chatRoomId}/send"), RealtimeMessageEvent::class.java)
      .map(RealtimeMessageEvent::transform)
  }
}