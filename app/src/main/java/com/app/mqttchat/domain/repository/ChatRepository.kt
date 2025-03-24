package com.app.mqttchat.domain.repository

import com.app.core.ApiResult
import com.app.mqttchat.data.model.RealtimeMessageEvent
import com.app.mqttchat.domain.model.ChatMessageModel
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
  fun sendMessage(chatRoomId: String, message: RealtimeMessageEvent)

  fun observeMessage(chatRoomId: String): Flow<ApiResult<ChatMessageModel>>
}