package com.example.chattembok.presentation.chatlist.model

import com.example.chattembok.backend.monggodb.dbmodel.ChatRoomModel

data class ChatModel (
  val shipmentId: String,
  val channelId: String
) {
  companion object {
    fun toDbModel(data: ChatModel): ChatRoomModel =
      ChatRoomModel(
        data.channelId,
        data.shipmentId
      )
  }
}