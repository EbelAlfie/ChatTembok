package com.example.chattembok.data.repository.chat

import com.example.chattembok.backend.monggodb.dbmodel.ChatRoomModel
import com.example.chattembok.backend.monggodb.dbmodel.ChatRoomModel.Companion
import com.example.chattembok.backend.monggodb.operation.ChatRoomDao
import com.example.chattembok.presentation.chatlist.model.ChatModel
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
  private val roomDao: ChatRoomDao
): ChatRepository {
  override suspend fun createChatRoom(chatModel: ChatModel) {
    TODO("Not yet implemented")
  }

  override suspend fun getChatRoom(nomorShipment: String): ChatModel {
    return ChatRoomModel.transform(
      roomDao.getChatRoom(nomorShipment)
    )
  }


}