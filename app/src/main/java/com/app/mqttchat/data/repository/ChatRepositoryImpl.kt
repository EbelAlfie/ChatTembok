package com.app.mqttchat.data.repository

import com.app.core.ApiResult
import com.app.mqttchat.data.model.MessageResponse
import com.app.mqttchat.domain.model.ChatMessageModel
import com.app.mqttchat.domain.repository.ChatRepository
import com.app.realtime.RealtimeClient
import com.app.realtime.model.PublishRequest
import com.app.realtime.model.SubscribeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
  private val realtimeApiClient: RealtimeClient
) : ChatRepository {

  override fun sendOnlineEvent() {

  }

  override fun sendMessage(chatRoomId: String, message: MessageResponse) {
    realtimeApiClient.publishMessage(
      PublishRequest.defaultPubRequest("chat/${chatRoomId}/send", message),
      MessageResponse::class.java
    )
  }

  override fun observeMessage(chatRoomId: String): Flow<ApiResult<ChatMessageModel>> {
    return realtimeApiClient
      .subscribeMessage(SubscribeRequest.defaultSubRequest("chat/${chatRoomId}/events"), MessageResponse::class.java)
      .catch { ApiResult.Error(it) }
      .map { ApiResult.Success(MessageResponse.transform(it)) }
  }
}