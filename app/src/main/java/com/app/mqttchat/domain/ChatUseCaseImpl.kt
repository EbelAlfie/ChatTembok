package com.app.mqttchat.domain

import com.app.mqttchat.domain.model.ChatMessageModel
import com.app.mqttchat.domain.repository.ChatRepository
import com.app.mqttchat.domain.usecase.ChatUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatUseCaseImpl @Inject constructor(
  private val chatRepository: ChatRepository
): ChatUseCase {
  override fun sendMessage(chatRoomId: String, message: ChatMessageModel) {
    val messageRequest = ChatMessageModel.toMessageRequest(message)
    chatRepository.sendMessage(chatRoomId, messageRequest)
  }

  override fun observeMessage(chatRoomId: String): Flow<ChatMessageModel> {
    return chatRepository.observeMessage(chatRoomId)
  }
}