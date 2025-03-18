package com.app.mqttchat.domain

import com.app.mqttchat.domain.repository.ChatRepository
import com.app.mqttchat.domain.usecase.ChatUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatUseCaseImpl @Inject constructor(
  private val chatRepository: ChatRepository
): ChatUseCase {
  override fun sendMessage(chatRoomId: String, message: String) {
    chatRepository.sendMessage(chatRoomId, message)
  }

  override fun observeMessage(chatRoomId: String): Flow<String> {
    return chatRepository.observeMessage(chatRoomId)
  }
}