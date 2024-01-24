package com.example.chattembok.data.repository.chat

import com.example.chattembok.presentation.chatlist.model.ChatModel

interface ChatRepository {

  suspend fun createChatRoom(chatModel: ChatModel)

  suspend fun getChatRoom(nomorShipment: String): ChatModel
}